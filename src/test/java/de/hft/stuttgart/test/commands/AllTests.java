package de.hft.stuttgart.test.commands;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestContainerCreation.class, TestContainerDeletion.class,
	TestContainerStart.class, TestContainerStop.class, TestGetAllContainer.class, TestImageCommands.class })
public class AllTests {

}
