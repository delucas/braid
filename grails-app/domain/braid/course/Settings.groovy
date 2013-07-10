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

	homework(0x1), assignment(0x2),
	questions(0x4), exams(0x8)
	
	Integer value
	
	public Option(Integer value) {
		this.value = value
	}
	
	Integer plus(Option option) {
		this.value + option.value
	}

}
