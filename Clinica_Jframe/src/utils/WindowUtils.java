package utils;

import javax.swing.JFrame;

public class WindowUtils {
	public static void switchToNewWindow(JFrame currentWindow, JFrame newWindow) {
		currentWindow.dispose();
		newWindow.setVisible(true);
	}
}
