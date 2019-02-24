package com.example.demo.dto;

import lombok.Getter;

public enum RESULT {

	HIGH("めっちゃとぶ"), //
	MID("ふつうにとぶ"), //
	LOW("ぜんぜんとばない");//

	@Getter
	private String dispWord;

	RESULT(String dispWord) {
		this.dispWord = dispWord;
	}

}
