
public class GetInput {
	private String name, singer, producer, MV, Lyrics, Background;

	public GetInput(String name, String singer, String producer, String MV, String Lyrics, String Background) {
		this.name = name;
		this.singer = singer;
		this.producer = producer;
		this.MV = MV;
		this.Lyrics = Lyrics;
		this.Background = Background;
		System.out.print(name + singer + producer + MV + Lyrics + Background);
	}

	public String getName() {
		return name;
	}

	public String getSinger() {
		return singer;
	}

	public String getProducer() {
		return producer;
	}

	public boolean getMV() {
		if (MV.equals("on"))
			return true;
		else
			return false;
	}

	public boolean getLyrics() {
		if (Lyrics.equals("on"))
			return true;
		else
			return false;
	}

	public boolean getBackground() {
		if (Background.equals("on"))
			return true;
		else
			return false;
	}

}
