package hudson.plugins.thisisfine;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.Test;

public class ThisIsFineFilterTest {

    ThisIsFineFilter fineFilter;

    @Before
    public void setup() {
        fineFilter = new ThisIsFineFilter();
    }

    @Test
    public void patternShouldMatch() {
        final Matcher m = fineFilter.patternBlue.matcher("/nocacheImages/48x48/blue.gif");
        assertThat(m.find(), is(true));
        assertThat(m.group(1), equalTo("48x48"));
        assertThat(m.group(2), equalTo(""));
        assertThat(m.group(3), equalTo("gif"));
    }

    @Test
    public void patternShouldMatchPNG() {
        final Matcher m = fineFilter.patternBlue.matcher("/nocacheImages/48x48/blue.png");
        assertThat(m.find(), is(true));
        assertThat(m.group(1), equalTo("48x48"));
        assertThat(m.group(2), equalTo(""));
        assertThat(m.group(3), equalTo("png"));
    }

    @Test
    public void patternShouldAlsoMatch() {
        final Matcher m = fineFilter.patternBlue.matcher("/nocacheImages/48x48/blue_anime.gif");
        assertThat(m.find(), is(true));
        assertThat(m.group(1), equalTo("48x48"));
        assertThat(m.group(2), equalTo("_anime"));
    }

    @Test
    public void patternShouldNotMatch() {
        final Matcher m = fineFilter.patternBlue.matcher("/nocacheImages/48x48/red_anime.gif");
        assertThat(m.find(), is(false));
    }
}
