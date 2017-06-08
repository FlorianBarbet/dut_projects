URL u = f[index].toURL();
//On créer un nouveau URLClassLoader pour charger le jar qui se trouve ne dehors du CLASSPATH
loader = new URLClassLoader(new URL[] {u}); 