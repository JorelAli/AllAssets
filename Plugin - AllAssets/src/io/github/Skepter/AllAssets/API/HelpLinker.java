package io.github.Skepter.AllAssets.API;

import io.github.Skepter.AllAssets.Commands.CommandHelp;

import java.lang.reflect.Method;

/** Links help documents to the help command
 * 
 * @author Skepter */
public class HelpLinker {

	public static void register(Object object) {
		for (Method method : object.getClass().getMethods()) {
			if (method.isAnnotationPresent(HelpDocumentation.class)) {
				HelpDocumentation d = method.getAnnotation(HelpDocumentation.class);
				CommandHelp.register(d.commandName(), d.helpDocumentation());
			}
		}
	}
	
	@HelpDocumentation(commandName = "command", helpDocumentation = {"/command this - does this", "/command that - does that"})
	public void onCommand() {
		
	}

}
