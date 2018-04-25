import java.util.ArrayList;
import java.util.List;

import MyBrowser.MyBrowser;
import MyBrowser.Selector;
import MyBrowser.Element;

public class FacebookListMerger {
    private static final MyBrowser browser = MyBrowser.getInstance();

    private static final String NAME_OF_LIST_TO_TAKE_FROM = "";
    private static final String NAME_OF_LIST_TO_ADD_TO    = "";
    private static final String USERNAME                  = "";
    private static final String PASSWORD                  = "";

    public static void main(String[] args) {
        browser.loadPage("https://www.facebook.com/");
        browser.setText(".//input[@id = 'email']", USERNAME);
        browser.setText(".//input[@id = 'pass']", PASSWORD);
        browser.click(".//input[@value = 'Log In']");
        browser.click(".//div[text() = 'Friend Lists']");
        browser.click(".//div[text() = 'Friend Lists']");
        browser.click(".//span[text() = '" + NAME_OF_LIST_TO_TAKE_FROM + "']");
        browser.click(".//a[text() = 'See All']");
        final List<Element> namesRaw = browser.findAll(Selector.byXPath(".//li/span[@class = 'text']"));
        final List<String>  names    = new ArrayList<>();

        for (final Element element : namesRaw) {
            names.add(element.getText());
        }

        browser.click(".//a[text() = 'Finish']");
        browser.click(".//div[text() = 'Friend Lists']");
        browser.click(".//span[text() = '" + NAME_OF_LIST_TO_ADD_TO + "']");
        browser.click(".//span[text() = 'Manage List']");
        browser.click(".//span[text() = 'Edit List']");
        browser.click(".//span[text() = 'On This List']");
        browser.click(".//span[text() = 'Friends']");

        for (String name : names) {
            browser.setText(".//button/following-sibling::div[1]/input", name);
            browser.click(".//div[@class = 'outline']/img");
        }

        System.out.println("Number of people in removed group: " + names.size());
        names.forEach(System.out::println);
    }
}
