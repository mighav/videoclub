package videoclub;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String urlcontent;
	private String description;
	private String year;
	private String director;
	private String actors;
	private String urlcover;
	private String valoration;
	
	
	// Default contructor (needed by SpringData)
	protected Film() {
		
	}
	
	public Film(String name, String urlcontent, String description,
			String year,String director,String actors, String urlcover, String valoration)	{
		this.name = name;
		this.urlcontent = urlcontent;
		this.description = description;
		this.year = year;
		this.director = director;
		this.actors = actors;
		this.urlcover = urlcover;
		this.valoration = valoration;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlcontent() {
		return urlcontent;
	}

	public void setUrlcontent(String urlcontent) {
		this.urlcontent = urlcontent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getUrlcover() {
		return urlcover;
	}

	public void setUrlcover(String urlcover) {
		this.urlcover = urlcover;
	}

	public String getValoration() {
		return valoration;
	}

	public void setValoration(String valoration) {
		this.valoration = valoration;
	}
	
	/*
	 * Añadidos
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Film: "+getId()+" "+getName()+" "+getUrlcontent()+" "+getDescription()
		+" "+getYear()+" "+getDirector()+" "+getActors()+" "+getUrlcover()+" "+getValoration();
	}
}
