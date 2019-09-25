
public class Test {

	public static void main(String[] args) {
		Converter conv = new Converter();
		conv.createShort("https://www.panelplace.com/blogs/practical-and-easy-ways-to-save-money");
		String sh = conv.getShortURL("https://www.panelplace.com/blogs/practical-and-easy-ways-to-save-money");
		String lg = conv.getLongURL(sh);
		System.out.println(sh);
		System.out.println(lg);
	}
	}
