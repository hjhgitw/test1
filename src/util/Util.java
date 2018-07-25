package util;

import javax.swing.ImageIcon;

public class Util {
	private static final String ICONS_PATH = "/view/icons/"; 

	public static ImageIcon getIcon(Class<?> kclass, String icone){
		return new ImageIcon(kclass.getResource(ICONS_PATH + icone + ".gif"));
	}
}
