//On créer une nouvelle instance de l'objet contenu dans la liste grâce à newInstance() 
//et on le cast en StringPlugins. Vu que la classe implémente StringPlugins, le cast est toujours correct
tmpPlugins[index] = (StringPlugins)((Class)this.classStringPlugins.get(index)).newInstance() ;