package org.pincraft

import com.thoughtcomplex.hairpin.CoreLoader
import com.thoughtcomplex.hairpin.ObfuscationMap
import com.thoughtcomplex.hairpin.classhackery.StaticAnalyzer

/**
 * Created by Falkreon on 7/4/2014.
 */
class Pincraft {
	private static ObfuscationMap obfuscation;
	public static void init() {
		//obfuscation = new StaticAnalyzer("cores\\minecraft_server.1.7.10.jar")

		/*
		CoreLoader.init("cores\\1.8.jar");
		CoreLoader.addJars(
				"cores\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\4.6\\jopt-simple-4.6.jar",
				"cores/libraries/com/google/code/gson/gson/2.2.4/gson-2.2.4.jar",
				"cores/libraries/com/google/guava/guava/17.0/guava-17.0.jar",
				"cores/libraries/org/lwjgl/lwjgl/lwjgl/2.9.1/lwjgl-2.9.1.jar",
				"cores/libraries/org/lwjgl/lwjgl/lwjgl-platform/2.9.1/lwjgl-platform-2.9.1-natives-windows.jar",
				"cores/libraries/org/apache/logging/log4j/log4j-core/2.0-rc2/log4j-core-2.0-rc2.jar",
				"cores/libraries/org/apache/logging/log4j/log4j-api/2.0-rc2/log4j-api-2.0-rc2.jar"
		);

		File platformNatives = new File("cores/libraries/org/lwjgl/lwjgl/lwjgl-platform/2.9.1/lwjgl-platform-2.9.1-natives-windows.jar");
		if (platformNatives.exists()) System.out.println("LWJGL platform natives found.");
		else System.out.println("### WARNING: PLATFORM NATIVES NOT FOUND ###");
		System.setProperty("org.lwjgl.librarypath", platformNatives.getAbsolutePath());
		*/
		obfuscation = new StaticAnalyzer("cores\\1.8.jar")
				.exclude("com.google","org.apache", "net.rudp", "it.unimi", "io.netty", "org.slf4j", "javassist", "java", "javax", "gnu"
		).map(new PincraftMapper());

		System.out.println(obfuscation.toSearge(  ));

		//Server.initialize();
		/*
		Class mainClass = obfuscation.getNormalClass("net.minecraft.client.main.Main");
		try {
			mainClass.invokeMethod("main", [] as String[]);
		} catch (Throwable t) {
			System.out.println("Pincraft had a problem and had to close. Details below:");
			t.printStackTrace();
		}*/
	}

	public static ObfuscationMap getObfuscation() {
		//don't run init, just put a dummy module in place as a stand-in for misbehaving code.
		if (obfuscation==null) obfuscation = new ObfuscationMap();
		return obfuscation;
	}

	public static void main(String[] args) {
		init();


	}
}
