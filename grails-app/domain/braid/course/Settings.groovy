package braid.course

class Settings {
	
	Integer options
	String bannerColor
	String githubUsername
	
	boolean has(Option option) {
		this.options & option.value
	}
}

enum Option {

	syllabus(0x1), homework(0x2), assignment(0x4)
	
	Integer value
	
	public Option(Integer value) {
		this.value = value
	}
	
	Integer plus(Option option) {
		this.value + option.value
	}

}
