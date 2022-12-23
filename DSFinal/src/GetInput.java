
public class GetInput {
	private String name, singer, producer;

	public GetInput(String name, String singer, String producer) {
		this.name = name;
		this.singer = singer;
		this.producer = producer;
		System.out.print(name + singer + producer);
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

}
