package edu.uah.itsc.cmac.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.json.JSONObject;
import org.osgi.framework.Bundle;

import edu.uah.itsc.aws.User;
import edu.uah.itsc.cmac.Activator;
import edu.uah.itsc.cmac.actions.XMPPClient;
import edu.uah.itsc.cmac.portal.PortalConnector;

// import com.swtdesigner.SWTResourceManager;

public class CreateAccountDialog {

	private Shell	loginShell;
	private Display	display;
	private LoginDialog loginDialog;

	public CreateAccountDialog(Shell loginShell, LoginDialog loginDialog) {
		this.loginShell = loginShell;
		this.loginDialog = loginDialog;
		this.display = loginShell.getDisplay();
	}

	public void createContents() {
		loginShell.setVisible(false);
		// Shell must be created with style SWT.NO_TRIM
		final Shell shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX) & (~SWT.MIN) & (~SWT.CLOSE));
		shell.setText("Create a new account");
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);

		Label logoLabel = new Label(shell, SWT.NONE);
		logoLabel.setImage(getImageFromPlugin("login.png"));

		Composite loginForm = new Composite(shell, SWT.BORDER);
		GridLayout loginFormLayout = new GridLayout(3, false);
		loginForm.setLayout(loginFormLayout);

		GridData loginLabelGridData = new GridData();
		loginLabelGridData.horizontalSpan = 3;
		loginLabelGridData.horizontalAlignment = SWT.CENTER;

		Label emptylabel1 = new Label(loginForm, SWT.NONE);
		emptylabel1.setLayoutData(loginLabelGridData);

		GridData textGridData = new GridData();
		textGridData.horizontalSpan = 2;
		textGridData.horizontalAlignment = SWT.LEFT;
		textGridData.grabExcessHorizontalSpace = true;
		textGridData.widthHint = 200;

		Label emailLabel = new Label(loginForm, SWT.NONE);
		emailLabel.setText("Email");
		final Text emailText = new Text(loginForm, SWT.BORDER);
		emailText.setMessage("Enter your email address");
		emailText.setLayoutData(textGridData);

		Label userLabel = new Label(loginForm, SWT.NONE);
		userLabel.setText("Username");
		final Text userText = new Text(loginForm, SWT.BORDER);
		userText.setMessage("Enter your desired username");
		userText.setLayoutData(textGridData);

		Label passLabel = new Label(loginForm, SWT.NONE);
		passLabel.setText("Password");
		final Text passText = new Text(loginForm, SWT.BORDER | SWT.PASSWORD);
		passText.setMessage("Enter your password");
		passText.setLayoutData(textGridData);

		Label confirmPassLabel = new Label(loginForm, SWT.NONE);
		confirmPassLabel.setText("Confirm Password");
		final Text confirmPassText = new Text(loginForm, SWT.BORDER | SWT.PASSWORD);
		confirmPassText.setMessage("Confirm your password");
		confirmPassText.setLayoutData(textGridData);

		Label emptylabel2 = new Label(loginForm, SWT.NONE);
		emptylabel2.setLayoutData(loginLabelGridData);

		Label emptylabel3 = new Label(loginForm, SWT.NONE);
		GridData buttonGridData = new GridData();
		buttonGridData.horizontalAlignment = SWT.CENTER;
		buttonGridData.widthHint = 100;

		Button loginButton = new Button(loginForm, SWT.PUSH);
		loginButton.setText("Create");
		loginButton.setLayoutData(buttonGridData);

		Button cancelButton = new Button(loginForm, SWT.PUSH);
		cancelButton.setText("Cancel");
		cancelButton.setLayoutData(buttonGridData);

		Label emptylabel4 = new Label(loginForm, SWT.NONE);
		emptylabel4.setLayoutData(loginLabelGridData);

		Label copyRightLabel = new Label(loginForm, SWT.NONE);
		copyRightLabel.setText("Copyright: ITSC, University of Alabama in Huntsville");
		copyRightLabel.setLayoutData(loginLabelGridData);

		shell.pack();
		setWindowPosition(shell);
		shell.open();

		loginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String username = userText.getText();
				String password = passText.getText();
				String password2 = confirmPassText.getText();
				String email = emailText.getText();

				if (!password.equals(password2)) {
					MessageDialog.openError(shell, "Error", "Password Mismatch");
				}
				else {
					PortalConnector pc = new PortalConnector();
					JSONObject jsonObject = pc.createAccount(username, password, email);

					if (jsonObject != null) {
						XMPPClient xc = new XMPPClient();
						xc.createUser(username, password, email, "");
						xc.setUsername(username);
						xc.setPassword(password);
						User.username = username;
						User.password = password;
						shell.close();
						loginDialog.proceedLogin(username, password, jsonObject);
					}
					else
						MessageDialog.openError(shell, "Error", "Could not create new account!");
				}
			}
		});

		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loginShell.setVisible(true);
				loginShell.setActive();
				shell.close();
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	private Image getImageFromPlugin(String imageName) {
		Bundle bundle = Activator.getDefault().getBundle();
		Path path = new Path(imageName);
		URL url = FileLocator.find(bundle, path, Collections.EMPTY_MAP);
		URL fileUrl = null;
		try {
			fileUrl = FileLocator.toFileURL(url);
			Image logoImage = new Image(display, fileUrl.getPath());
			return logoImage;
		}
		catch (IOException e) {
			System.out.println(e.toString());
			return null;
		}
	}

	private void setWindowPosition(Shell shell) {
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shell.setLocation(x, y);
	}
}