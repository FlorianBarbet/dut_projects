tmp = tmp.substring(0,tmp.length()-6);
tmp = tmp.replaceAll("/",".");
 
tmpClass = Class.forName(tmp ,true,loader);