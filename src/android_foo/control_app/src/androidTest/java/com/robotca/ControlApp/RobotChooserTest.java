package com.robotca.ControlApp;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.robotca.ControlApp.Core.RobotInfo;
import com.robotca.ControlApp.Core.RobotStorage;

import java.util.UUID;

/**
 * Rudimentary instrumentation test that tests adding and removing RobotInfo's int the RobotChooser Activity.
 * Created by Nathaniel Stone on 3/3/16.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RobotChooserTest {

    private RobotInfo robotInfo;

    @Rule
    public ActivityTestRule<RobotChooser> robotChooserRule = new ActivityTestRule<>(RobotChooser.class);


    @Before
    public void setUp() throws Exception {
        robotInfo = new RobotInfo(UUID.randomUUID(), "TestRobotInfo", "TestMasterURI", null, null, null, null, null, null, false, false, false, false);
    }

    @Test
    public void testA_AddRobot() throws Exception {

        final RobotChooser robotChooser = robotChooserRule.getActivity();
        final int size = RobotStorage.getRobots().size();

        robotChooser.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                robotChooser.addRobot(robotInfo);

                assertEquals(size + 1, RobotStorage.getRobots().size());
            }
        });
    }

    @Test
    public void testB_RemoveRobot() throws Exception {

        final RobotChooser robotChooser = robotChooserRule.getActivity();
        final int size = RobotStorage.getRobots().size();

        robotChooser.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                robotChooser.removeRobot(size - 1);

                assertEquals(size - 1, RobotStorage.getRobots().size());
            }
        });
    }
}
