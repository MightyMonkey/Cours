package sgbd.stockage;

public interface Table {
	
	public Nuplet get(int pos);
	public Nuplet[] fullScan();
	public Nuplet[] getByAtt(int att, Object value); // R�cup�re les nuplets qui ont la valeur value sur l'attribut nr att
	public int size();
	public void put(Nuplet n);
	public Nuplet[] insert(Nuplet n);
	public Nuplet[] delete(int att, Object value); // efface tous les Nuplets dont att est �gal � value
	public Nuplet[] update(int att, Object oldValue, Object newValue); // modifie tous les Nuplets dont att �tait �gal � oldValue et leur met la valeur newValue

}
