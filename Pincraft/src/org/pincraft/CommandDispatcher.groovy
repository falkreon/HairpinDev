package org.pincraft

import com.thoughtcomplex.hairpin.ObfuscationMap

import java.lang.reflect.Method

/**
 * org.pincraft.CommandDispatcher - opaque wrapper/composition of ICommandHandler / org.pincraft.CommandDispatcher / CommandHandler.
 *
 * You normally get the proper instance of this object from <code>org.pincraft.org.pincraft.Server.getCommandDispatcher();</code>
 *
 *
 * Created by Falkreon on 7/4/2014.
 */
class CommandDispatcher {
	private static Native dispatcher = null;
	protected CommandDispatcher bind(Object o, ObfuscationMap obfuscation) {
		Class minecraftClass = obfuscation.getNormalClass("net.minecraft.server.MinecraftServer");


		Class commandHandlerClass = Pincraft.getObfuscation().getObfuscatedClass("CommandHandler");
		Class commandListenerInterface = Pincraft.getObfuscation().getObfuscatedClass("ICommandListener");
		Method executeMethod = o.getClass().getMethods().find {
			it.getDeclaringClass().equals(commandHandlerClass) &&
			it.getParameterTypes().equals([commandListenerInterface, String.class]) &&
			it.getReturnType().equals(Integer.TYPE);
		};
		o.metaClass.methods[executeMethod.getName()] = { commandlistener , String s ->
			//Short-circuit to our classes
			List<String> pieces = s.split(" ") as List<String>;
			System.out.println("Pieces! "+pieces.toListString());

			executeMethod.invoke(commandlistener, s);
		}

		dispatcher = o as Native;
	}
	static interface Native {

	}
}
