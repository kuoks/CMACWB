package edu.uah.itsc.cmac.actions;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.uah.itsc.aws.S3;
import edu.uah.itsc.aws.User;
import edu.uah.itsc.cmac.portal.PortalPost;
import edu.uah.itsc.cmac.portal.PortalUtilities;
import edu.uah.itsc.cmac.portal.Workflow;
import edu.uah.itsc.cmac.ui.NavigatorView;

public class ShareCommandHandler extends AbstractHandler {
	private IStructuredSelection selection = StructuredSelection.EMPTY;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		selection = (IStructuredSelection) HandlerUtil
				.getCurrentSelectionChecked(event);

		Object object = selection.getFirstElement();
		// Job job = new Job("Sharing...") {
		// protected IStatus run(final IProgressMonitor monitor) {

		if (selection.size() == 1) {

			final Object firstElement = selection.getFirstElement();
			if (firstElement instanceof IFile) {
				MessageDialog.openInformation(Display.getDefault()
						.getActiveShell(), "Information",
						"You can only share folders!");
			} else if (firstElement instanceof IFolder) {
				final IFolder selectedFolder = (IFolder) firstElement;

				final String path = selectedFolder.getFullPath().toString();

				final Shell shell = new Shell();
				shell.setText("Workflow Settings");
				shell.setLayout(new GridLayout(2, false));
				Label title = new Label(shell, SWT.NONE);
				title.setText("Title : ");
				final Text titleText = new Text(shell, SWT.BORDER);
				addSpanData(titleText);
				Label description = new Label(shell, SWT.NONE);
				description.setText("Description : ");
				final Text descText = new Text(shell, SWT.MULTI | SWT.BORDER
						| SWT.WRAP | SWT.V_SCROLL);
				GridData data = new GridData(GridData.FILL_BOTH);
				data.horizontalSpan = 2;
				descText.setLayoutData(data);
				/*
				 * Shreedhan Add keyword label and keyword text
				 */
				Label keywordLabel = new Label(shell, SWT.NONE);
				keywordLabel.setText("Keywords");
				final Text keywordText = new Text(shell, SWT.BORDER);
				addSpanData(keywordText);

				org.eclipse.swt.widgets.Button ok = new org.eclipse.swt.widgets.Button(
						shell, SWT.PUSH);
				ok.setText("  OK  ");
				org.eclipse.swt.widgets.Button cancel = new org.eclipse.swt.widgets.Button(
						shell, SWT.PUSH);
				cancel.setText("Cancel");
				final String nodeID;
				HashMap<String, String> nodeMap = PortalUtilities.getPortalWorkflowDetails(path);
				if (nodeMap != null){
					nodeID = nodeMap.get("nid");
					titleText.setText((String)nodeMap.get("title"));
					keywordText.setText((String)nodeMap.get("keywords"));
					descText.setText(((String)nodeMap.get("description")).replaceAll("\\<.*?\\>", ""));
				}
				else nodeID = null;
				
				ok.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						try {

							System.out.println("Button clicked");
							PortalPost portalPost = new PortalPost();

							Workflow workflow = new Workflow(titleText
									.getText(), descText.getText(), keywordText
									.getText());
							workflow.setPath(path);
							workflow.setShared(true);
							System.out.println(workflow.getJSON());
							if (nodeID != null){
								portalPost.put(PortalUtilities.getNodeRestPoint()+ nodeID,
										workflow.getJSON());
							}
							else {
								portalPost
								.post(PortalUtilities.getNodeRestPoint(),
										workflow.getJSON());
							}
							portalPost.runCron();
							Job job = new Job("Sharing..."){
					        	protected IStatus run(IProgressMonitor monitor){
					        		
									if(selection.size() == 1) {
										
										 Object firstElement = selection.getFirstElement();
										 if(firstElement instanceof IFile  ) {
					                       MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Information", "You can only share folders!");
											}
										 else if (firstElement instanceof IFolder){
												S3 s3 = new S3();
												
												s3.uploadFolder((IFolder)firstElement);
												if (!s3.userFolderExists(User.username, S3.communityBucketName))
													s3.uploadUserFolder(User.username, S3.communityBucketName);
												s3.shareFolder((IFolder)firstElement);
												try{
												NavigatorView view = (NavigatorView) getPage().findView("edu.uah.itsc.cmac.NavigatorView");
												view.refreshCommunityResource();
												}
												catch(Exception e){
													System.out.println("Errror while refreshCommunityResource "+e.toString());
												}
												IProject communityProject = ResourcesPlugin.getWorkspace().getRoot().getProject(s3.getCommunityBucketName());
												try{
													communityProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);
												}
												catch(CoreException e)
												{
													e.printStackTrace();
												}
										 }
									 
										 }
					        		monitor.done();
					        		return Status.OK_STATUS;
					        	}
					        		};
					        job.setUser(true);
					        job.schedule();
						} catch (Exception e) {
							MessageDialog.openError(shell, "Error",
									e.getMessage());
						}
						shell.close();
					}
				});
				cancel.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						shell.close();
					}
				});

				shell.setSize(500, 400);

				shell.open();

			}

		}
		// monitor.done();
		// return Status.OK_STATUS;
		// }
		// };
		// job.setUser(true);
		// job.schedule();

		return object;
	}

	private void addSpanData(Control comp) {
		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		comp.setLayoutData(data);
	}

	/**
	 * Returns the active page or null if no page is available. A page is a
	 * composition of views and editors which are meant to show at the same
	 * time.
	 * 
	 * @return The active page.
	 */
	public static IWorkbenchPage getPage() throws Exception {
		final IWorkbenchPage page[] = new IWorkbenchPage[] { null };
		final String errorMessages[] = new String[] { "" };

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					IWorkbench wb = PlatformUI.getWorkbench();
					IWorkbenchWindow wbWindow = wb.getActiveWorkbenchWindow();
					page[0] = wbWindow.getActivePage();
				} catch (Exception e) {
					errorMessages[0] = e.toString();
				}
			}
		});
		if (errorMessages[0].length() > 0) {
			throw new Exception(errorMessages[0]);
		}
		return page[0];
	}

}