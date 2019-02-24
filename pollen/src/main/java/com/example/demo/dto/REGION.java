package com.example.demo.dto;

import java.util.Objects;

public enum REGION {
	Hokkaidō("Hokkaidō","北海道","北海道"),
	Aomori("Aomori-ken","青森","東北"),
	Iwate("Iwate-ken","岩手","東北"),
	Miyagi("Miyagi-ken","宮城","東北"),
	Akita("Akita-ken","秋田","東北"),
	Yamagata("Yamagata-ken","山形","東北"),
	Fukushima("Fukushima-ken","福島","東北"),
	Ibaraki("Ibaraki-ken","茨城","関東"),
	Tochigi("Tochigi-ken","栃木","関東"),
	Gunma("Gunma-ken","群馬","関東"),
	Saitama("Saitama-ken","埼玉","関東"),
	Chiba("Chiba-ken","千葉","関東"),
	Tokyo("Tokyo","東京","関東"),
	Kanagawa("Kanagawa-ken","神奈川","関東"),
	Niigata("Niigata-ken","新潟","中部"),
	Toyama("Toyama-ken","富山","中部"),
	Ishikawa("Ishikawa-ken","石川","中部"),
	Fukui("Fukui-ken","福井","中部"),
	Yamanashi("Yamanashi-ken","山梨","中部"),
	Nagano("Nagano-ken","長野","中部"),
	Gifu("Gifu-ken","岐阜","中部"),
	Shizuoka("Shizuoka-ken","静岡","中部"),
	Aichi("Aichi-ken","愛知","中部"),
	Mie("Mie-ken","三重","近畿"),
	Shiga("Shiga-ken","滋賀","近畿"),
	Kyoto("Kyoto","京都","近畿"),
	Ōsaka("Ōsaka","大阪","近畿"),
	Hyōgo("Hyōgo-ken","兵庫","近畿"),
	Nara("Nara-ken","奈良","近畿"),
	Wakayama("Wakayama-ken","和歌山","近畿"),
	Tottori("Tottori-ken","鳥取","中国"),
	Shimane("Shimane-ken","島根","中国"),
	Okayama("Okayama-ken","岡山","中国"),
	Hiroshima("Hiroshima-ken","広島","中国"),
	Yamaguchi("Yamaguchi-ken","山口","中国"),
	Tokushima("Tokushima-ken","徳島","四国"),
	Kagawa("Kagawa-ken","香川","四国"),
	Ehime("Ehime-ken","愛媛","四国"),
	Kōchi("Kōchi-ken","高知","四国"),
	Fukuoka("Fukuoka-ken","福岡","九州"),
	Saga("Saga-ken","佐賀","九州"),
	Nagasaki("Nagasaki-ken","長崎","九州"),
	Kumamoto("Kumamoto-ken","熊本","九州"),
	Ōita("Ōita-ken","大分","九州"),
	Miyazaki("Miyazaki-ken","宮崎","九州"),
	Kagoshima("Kagoshima-ken","鹿児島","九州"),
	Okinawa("Okinawa-ken","沖縄","九州"),;

	private final String enName;
	private final String jpName;
	private final String region;

	private REGION(String enName, String jpName, String region) {
		this.enName = enName;
		this.jpName = jpName;
		this.region = region;
	}
	
	/**
	 * Convert region Japanese name from English name  
	 * @param name
	 * @return
	 */
	public static String toJpName(String name) {
		Objects.requireNonNull(name);
		
		for (REGION region : REGION.values()) {
			if(region.enName.equals(name)) {
				return region.jpName;
			}
		}
		// not detect jpName
		throw new IllegalArgumentException("入力値が不正です [" + name +"]");
	}
}
