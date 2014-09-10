package org.pincraft

import com.thoughtcomplex.hairpin.ObfuscationMap

import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * Created by Falkreon on 7/3/2014.
 */
class Server {
	private static Native server = null;
	private static CommandDispatcher commandDispatcher = null;
	private static ObfuscationMap map = null;

	static void initialize(ObfuscationMap map) {
		Server.map = map;
	}

	private static Native getServer() {
		if (server!=null) return server;
		Class serverClass = map.getNormalClass("net.minecraft.server.MinecraftServer");
		Method getServerMethod = serverClass.getDeclaredMethods().find { it ->
			it.getReturnType().equals(serverClass) &&
					(it.getParameterTypes().length==0);
		}
		final Object o = getServerMethod.invoke(null);
		if (o==null) return null;

		//TODO: Insert hooks
		o.metaClass.getCommandHandler << { ->
			final Class handlerInterfaceClass = map.getObfuscatedClass("ICommandHandler");
			Field commandHandlerField = serverClass.getFields().find { it ->
				it.getDeclaringClass().equals(serverClass) &&
				it.getType().equals(handlerInterfaceClass);
			}
			return commandHandlerField.get(o);
		};

		server = o as Native;
		return server;
	}

	public static CommandDispatcher getCommandDispatcher() {
		if (commandDispatcher==null) {
			final Object handler = getServer().getCommandHandler();
			commandDispatcher = new CommandDispatcher(handler);
		}
		return commandDispatcher;
	}

	static interface Native {
		public Object getCommandHandler();
	}
}
