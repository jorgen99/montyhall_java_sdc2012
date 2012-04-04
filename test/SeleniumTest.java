import play.libs.F;
import play.test.TestBrowser;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

/**
 * 2012-04-02
 */
public class SeleniumTest {

    @Test
    public void runInBrowser() {
        running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                String h1Text = browser.$(".logo h1").getTexts().get(0).toString();
                assertThat(h1Text).isEqualTo("Play the Monty Hall Game!");
            }
        });
    }
}
