package edu.uah.itsc.cmac.programview.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uah.itsc.cmac.portal.PortalUtilities;
import edu.uah.itsc.cmac.portal.Program;
import edu.uah.itsc.cmac.programview.DND.FavoritesDragSource;

public class ProgramView extends ViewPart {
	public static final String ID = "edu.uah.itsc.cmac3.ui.Tableview";
	private TableViewer tableviewer;
	public List<Program> programsList;
	private Action openProgramView;

	// getter and setter for the programs list
	// -----------------------------------------
	public List<Program> getProgramsList() {
		return programsList;
	}

	public void setProgramsList(List<Program> programsList) {
		this.programsList = programsList;
	}

	// -------------------------------------------

	public ProgramView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		try {
			dragAndDrop(parent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		makeActions();
	}

	@Override
	public void setFocus() {

	}

	private void dragAndDrop(Composite parent) throws Exception {
		// IEditorPart myeditor = null;
		// try {
		// myeditor = PlatformUI
		// .getWorkbench()
		// .getActiveWorkbenchWindow()
		// .getActivePage()
		// .openEditor(new StringEditorInput(""),
		// "org.eclipse.ui.DefaultTextEditor");
		//
		// } catch (PartInitException e) {
		// e.printStackTrace();
		// }

		ArrayList<Program> progs = getPrograms();

		// ---------- code modified - lsamudrala ----------

		/**
		 * Download all the programs and create a program object with the data
		 * for each program
		 */

		programsList = progs;
		System.out.println("Begining to read the programs ..");

		// Start a new thread to download all the programs
		ReadProgramsThread rpt = new ReadProgramsThread(progs);
		rpt.start();

		// -----------end of code modifications -----------

		int operations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
		// For Drag
		tableviewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);

		// code commented - lsamudrala-------------------------
		// tableviewer.addDragSupport(operations, transferTypes,
		// new ProgramDragListener(tableviewer, progs));
		// -----------------------------------------------------

		// --------code modified - lsamudrala----
		// Set the Drag Source
		new FavoritesDragSource(tableviewer);
		// --------------------------------------

		for (int i = 0; i < progs.size(); i++) {

			if (progs.get(i) != null)
				tableviewer.add(progs.get(i).getTitle());
		}
	}

	// changes private to public to access this from workflow project
	public ArrayList<Program> getPrograms() {
		String data = PortalUtilities.getDataFromURL(PortalUtilities
				.getNodeRestPoint() + "?parameters[type]=program&pagesize=1000000");
		ArrayList<Program> programs = new ArrayList<Program>();
		try {
			JSONArray jsonData = new JSONArray(data);
			int i = 0, numElements = jsonData.length();
			for (i = 0; i < numElements; i++) {
				JSONObject jsonElement = (JSONObject) jsonData.get(i);
				Program program = new Program(jsonElement.getString("title"),
						jsonElement.getString("uri"));
				programs.add(program);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// System.out.println(data);
		return programs;
	}

	private void makeActions() {
		openProgramView = new Action() {
			private IViewPart showView;

			public void run() {
				try {
					showView = PlatformUI
							.getWorkbench()
							.getActiveWorkbenchWindow()
							.getActivePage()
							.showView(
									"edu.uah.itsc.programformview.views.ProgramFormView");

				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		};
		openProgramView.setText("Add Program");
		openProgramView.setToolTipText("Add Program");
		ImageDescriptor programImageDescriptor = new ImageDescriptor() {
			
			@Override
			public ImageData getImageData() {
				Image programImage = new Image(
					getSite().getShell().getDisplay(), getClass().getClassLoader()
					.getResourceAsStream("icons/page_add.png"));
				return programImage.getImageData();
			}
		};
		openProgramView.setImageDescriptor(programImageDescriptor);
//		openProgramView.setImageDescriptor(PlatformUI.getWorkbench()
//				.getSharedImages()
//				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		IActionBars bars = getViewSite().getActionBars();
		IToolBarManager toolBarManager = bars.getToolBarManager();
		toolBarManager.add(openProgramView);
	}

}
