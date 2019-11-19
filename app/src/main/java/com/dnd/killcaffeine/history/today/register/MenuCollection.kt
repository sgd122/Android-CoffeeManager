/*
 * Created by Lee Oh Hyoung on 2019/11/19.
 */
package com.dnd.killcaffeine.history.today.register

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.data.room.menu.Menu

object MenuCollection {

    private const val STARBUCKS: String = "스타벅스"
    private const val EDIYA: String = "이디야"
    private const val HOLLYS: String = "할리스"
    private const val ANGELINUS: String = "엔젤리너스"

    fun starBucks(): ArrayList<Menu> = arrayListOf(
        Menu("나이트로 쇼콜라", STARBUCKS, 245, R.drawable.starbucks_nitro_chocolat),
        Menu("나이트로 콜드브루", STARBUCKS, 245, R.drawable.starbucks_nitro_coldbrew),
        Menu("돌체 콜드 브루", STARBUCKS, 150, R.drawable.starbucks_dolce_cold_brew),
        Menu("바닐라 크림 콜드브루", STARBUCKS, 150, R.drawable.starbucks_vanilla_cream_cold_brew),
        Menu("콜드브루", STARBUCKS, 150, R.drawable.starbucks_cold_brew),
        Menu("콜드브루 몰트", STARBUCKS, 150, R.drawable.starbucks_cold_brew_malt),
        Menu("콜드브루 플로트", STARBUCKS, 150, R.drawable.starbucks_cold_brew_float),
        Menu("콜드 폼 콜드브루", STARBUCKS, 150, R.drawable.starbucks_cold_foam_cold_brew),
        Menu("아이스 커피", STARBUCKS, 140, R.drawable.starbucks_iced_coffee),
        Menu("오늘의 커피", STARBUCKS, 260, R.drawable.starbucks_brewed_coffee),
        Menu("단호박 라떼", STARBUCKS, 75, R.drawable.starbucks_sweet_pumkin_lattee),
        Menu("아이스 토피넛 라떼", STARBUCKS, 75, R.drawable.starbucks_iced_toffee_nut_latte),
        Menu("토피넛 라떼", STARBUCKS, 75, R.drawable.starbucks_toffee_nut_latte),
        Menu("에스프레소 콘 파나", STARBUCKS, 75, R.drawable.starbucks_espresso_con_panna),
        Menu("에스프레소 마끼야", STARBUCKS, 75, R.drawable.starbucks_espresso_macchiato),
        Menu("아이스 아메리카노", STARBUCKS, 150, R.drawable.starbucks_iced_americano),
        Menu("따뜻한 아메리카노", STARBUCKS, 150, R.drawable.starbucks_caffe_americano),
        Menu("아이스 블론드 코코아 클라우드 마끼야또", STARBUCKS, 170, R.drawable.starbucks_iced_blonde_cocoa_macchiato),
        Menu("아이스 카라멜 마끼야또", STARBUCKS, 75, R.drawable.starbucks_iced_caramel_macchiato),
        Menu("따뜻한 카라멜 마끼야또", STARBUCKS, 75, R.drawable.starbucks_caramel_macchiato),
        Menu("아이스 카푸치노", STARBUCKS, 75, R.drawable.starbucks_iced_cappuccino),
        Menu("따뜻한 카푸치노", STARBUCKS, 75, R.drawable.starbucks_cappuccino),
        Menu("라벤더 카페 브레베", STARBUCKS, 75, R.drawable.starbucks_lavender_cafe_breve),
        Menu("아이스 라벤더 카페 브레베", STARBUCKS, 75, R.drawable.starbucks_iced_lavender_cafe_breve),
        Menu("럼 샷 코르타도", STARBUCKS, 75, R.drawable.starbucks_rum_shot_cortado),
        Menu("돌체 라떼", STARBUCKS, 150, R.drawable.starbucks_dolce_latte),
        Menu("아이스 돌체 라떼", STARBUCKS, 75, R.drawable.starbucks_iced_dolce_latte),
        Menu("카페 라떼", STARBUCKS, 75, R.drawable.starbucks_caffe_latte),
        Menu("아이스 카페 모카", STARBUCKS, 95, R.drawable.starbucks_iced_caffe_mocha),
        Menu("아이스 화이트 초콜릿 모카", STARBUCKS, 75, R.drawable.starbucks_iced_white_chocolate_mocha),
        Menu("카페 모카", STARBUCKS, 95, R.drawable.starbucks_caffe_mocha),
        Menu("화이트 초콜릿 모카", STARBUCKS, 75, R.drawable.starbucks_white_chocolate_mocha),
        Menu("바닐라 플랫 화이트", STARBUCKS, 260, R.drawable.starbucks_vanila_flat_white),
        Menu("바닐라 더블 샷", STARBUCKS, 145, R.drawable.starbucks_double_shot),
        Menu("카라멜 에스프레소 더블 샷", STARBUCKS, 150, R.drawable.starbucks_double_shot),
        Menu("커피 더블 샷", STARBUCKS, 150, R.drawable.starbucks_double_shot),
        Menu("헤이즐넛 더블 샷", STARBUCKS, 150, R.drawable.starbucks_double_shot),
        Menu("블론드 리스트레토 미끼야또", STARBUCKS, 150, R.drawable.starbucks_blonde_ristretto_macchiato),
        Menu("블론드 에스프레소 토닉", STARBUCKS, 170, R.drawable.starbucks_blonde_espresso_tonic),
        Menu("아이스 블론드 리스트레토 미끼야또", STARBUCKS, 150, R.drawable.starbucks_iced_blonde_ristretto_macchiato),
        Menu("에스프레소", STARBUCKS, 75, R.drawable.starbucks_espresso),
        Menu("클래식 아포가토", STARBUCKS, 0, R.drawable.starbucks_classic_affogato),
        Menu("토피 넛 프라푸치노", STARBUCKS, 85, R.drawable.starbucks_toffee_nut_frappuccino),
        Menu("민트 초콜릿 프라푸치노", STARBUCKS, 14, R.drawable.starbucks_mint_chocolate_cream_frappuccino),
        Menu("모카 프라푸치노", STARBUCKS, 90, R.drawable.starbucks_mocha_frappuccino),
        Menu("에스프레소 프라푸치노", STARBUCKS, 120, R.drawable.starbucks_esspresso_frappuccino),
        Menu("이천 햅쌀 커피 프라푸치노", STARBUCKS, 85, R.drawable.starbucks_icheon_rice_coffee_frappuccino),
        Menu("자바칩 프라푸치노", STARBUCKS, 100, R.drawable.starbucks_java_chip_frappuccino),
        Menu("카라멜 프라푸치노", STARBUCKS, 85, R.drawable.starbucks_caramel_frappuccino),
        Menu("화이트 초콜릿 모카 프라푸치노", STARBUCKS, 85, R.drawable.starbucks_white_chocolate_mocha_frappuccino),
        Menu("그린 티 크림 프라푸치노", STARBUCKS, 95, R.drawable.starbucks_green_tea_cream_frappuccino),
        Menu("바닐라 크림 프라푸치노", STARBUCKS, 0, R.drawable.starbucks_vanilla_cream_frappuccino),
        Menu("이천 햅쌀 크림 프라푸치노", STARBUCKS, 0, R.drawable.starbucks_icheon_rice_cream_frappuccino),
        Menu("제주 까망 크림 프라푸치노", STARBUCKS, 0, R.drawable.starbucks_jeju_black_sesame_cream_frappuccino),
        Menu("제주 쑥떡 크림 프라푸치노", STARBUCKS, 0, R.drawable.starbucks_jeju_mugwort_cream_frappuccino),
        Menu("초콜릿 크림 칩 프라푸치노", STARBUCKS, 10, R.drawable.starbucks_chocolate_cream_chip_frappuccino),
        Menu("초콜릿 크림 프라푸치노", STARBUCKS, 10, R.drawable.starbucks_chocolate_cream_frappuccino),
        Menu("화이트 딸기 크림 프라푸치노", STARBUCKS, 0, R.drawable.starbucks_white_strawberry_cream_frappuccino),
        Menu("화이트 타이거 프라푸치노", STARBUCKS, 0, R.drawable.starbucks_white_tiger_frappuccino),
        Menu("망고 패션 후르츠 블렌디드", STARBUCKS, 35, R.drawable.starbucks_mango_passion_fruit_blended),
        Menu("딸기 요거트 블렌디드", STARBUCKS, 0, R.drawable.starbucks_strawberry_yogurt_blended),
        Menu("익스트림 티 블렌디드", STARBUCKS, 0, R.drawable.starbucks_extreme_tea_blended),
        Menu("자몽 샤베트 블렌디드", STARBUCKS, 0, R.drawable.starbucks_grapefruit_sherbet_blended),
        Menu("매직 팝 스플래쉬 피지오", STARBUCKS, 0, R.drawable.starbucks_magic_pop_splash_fizzio),
        Menu("블랙 티 레모네이드 피지오", STARBUCKS, 35, R.drawable.starbucks_black_tea_lemonade_fizzio),
        Menu("쿨 라임 피지오", STARBUCKS, 60, R.drawable.starbucks_cool_line_fizzio),
        Menu("패션 탱고 티 레모네이드 피지오", STARBUCKS, 60, R.drawable.starbucks_passion_tango_tea_lemonade_fizzio),
        Menu("핑크 자몽 피지오", STARBUCKS, 0, R.drawable.starbucks_pink_grape_fruit_fizzio),
        Menu("아이스 유자 민트 티", STARBUCKS, 0, R.drawable.starbucks_iced_yuja_mint_tea),
        Menu("유자 민트 티", STARBUCKS, 0, R.drawable.starbucks_yuja_mint_tea),
        Menu("그랜마 애플 블랙 밀크 티", STARBUCKS, 30, R.drawable.starbucks_granma_apple_black_milk_tea),
        Menu("돌체 블랙 밀크 티", STARBUCKS, 45, R.drawable.starbucks_dolce_black_milk_tea),
        Menu("아이스 그랜마 애플 블랙 밀크 티", STARBUCKS, 30, R.drawable.starbucks_iced_grandma_apple_black_milk_tea),
        Menu("아이스 돌체 블랙 밀크 티", STARBUCKS, 45, R.drawable.starbucks_iced_dolce_black_milk_tea),
        Menu("그랜마 애플 블랙 티", STARBUCKS, 45, R.drawable.starbucks_grandma_apple_black_tea),
        Menu("라임 패션 티", STARBUCKS, 150, R.drawable.starbucks_lime_passion_tea),
        Menu("민트 블렌드 티", STARBUCKS, 45, R.drawable.starbucks_mint_blended_brewed_tea),
        Menu("아이스 그랜마 애플 블랙 티", STARBUCKS, 40, R.drawable.starbucks_iced_grandma_apple_black_tea),
        Menu("아이스 라임 패션 티", STARBUCKS, 93, R.drawable.starbucks_iced_lime_passion_tea),
        Menu("아이스 민트 블렌드 티", STARBUCKS, 0, R.drawable.starbucks_iced_mint_blended_brewed_tea),
        Menu("아이스 유스베리 티", STARBUCKS, 20, R.drawable.starbucks_iced_youthberry_brewed_tea),
        Menu("아이스 잉글리쉬 브랙퍼스트 티", STARBUCKS, 45, R.drawable.starbucks_iced_english_breakfast_brewed_tea),
        Menu("아이스 제주 유기 녹차", STARBUCKS, 16, R.drawable.starbucks_iced_jeju_organic_green_tea),
        Menu("아이스 제주 유자 탱고 티", STARBUCKS, 0, R.drawable.starbucks_iced_jeju_yuzu_tango_tea),
        Menu("아이스 차이 티", STARBUCKS, 45, R.drawable.starbucks_iced_chai_brewed_tea),
        Menu("아이스 캐모마일 블렌드 티", STARBUCKS, 0, R.drawable.starbucks_iced_chamomile_bleded_brewed_tea),
        Menu("아이스 히비스커스 블렌드 티", STARBUCKS, 0, R.drawable.starbucks_iced_hibiscus_blended_brewed_tea),
        Menu("유스베리 티", STARBUCKS, 20, R.drawable.starbucks_youthberry_brewed_tea),
        Menu("잉글리쉬 브렉퍼스트 티", STARBUCKS, 45, R.drawable.starbucks_english_breakfast_brewed_tea),
        Menu("자몽 허니 블랙 티", STARBUCKS, 40, R.drawable.starbucks_grapefruit_honey_black_tea),
        Menu("제주 유기 녹차 티", STARBUCKS, 16, R.drawable.starbucks_jeju_organic_green_tea),
        Menu("제주 유자 탱고 티", STARBUCKS, 0, R.drawable.starbucks_jeju_yuzu_tango_tea),
        Menu("차이 티", STARBUCKS, 45, R.drawable.starbucks_chai_brewed_tea),
        Menu("캐모마일 블렌드 티", STARBUCKS, 0, R.drawable.starbucks_chamomile_blended_brewed_tea),
        Menu("히비스커스 티", STARBUCKS, 0, R.drawable.starbucks_hibiscus_blended_brewed_tea),
        Menu("썸머 시트러스 소르베 그린 티", STARBUCKS, 5, R.drawable.starbucks_summer_citrus_sorbet_green_tea),
        Menu("썸머 스트러스 아이스 그린 티", STARBUCKS, 7, R.drawable.starbucks_summer_citrus_iced_green_tea),
        Menu("아이스 자몽 허니 블랙 티", STARBUCKS, 35, R.drawable.starbucks_iced_grapefruit_honey_black_tea),
        Menu("그린 티 라떼", STARBUCKS, 95, R.drawable.starbucks_green_tea_latte),
        Menu("아이스 그린 티 라떼", STARBUCKS, 95, R.drawable.starbucks_iced_green_tea_latte),
        Menu("아이스 샷 그린 티 라떼", STARBUCKS, 170, R.drawable.starbucks_iced_shot_green_tea_latte),
        Menu("아이스 제주 말차 샷 라떼", STARBUCKS, 107, R.drawable.starbucks_iced_jeju_matcha_shot_latte),
        Menu("아이스 차이 티 라떼", STARBUCKS, 75, R.drawable.starbucks_iced_chai_tea_latte),
        Menu("제주 말차 샷 라떼", STARBUCKS, 107, R.drawable.starbucks_jeju_matcha_shot_latte),
        Menu("아이스 그린 티 라떼", STARBUCKS, 95, R.drawable.starbucks_iced_green_tea_latte),
        Menu("차이 티 라떼", STARBUCKS, 75, R.drawable.starbucks_chai_tea_latte),
        Menu("시그니처 핫 초콜릿", STARBUCKS, 15, R.drawable.starbucks_signature_hot_chocolate),
        Menu("아이스 시크니처 초콜릿", STARBUCKS, 15, R.drawable.starbucks_iced_signature_chocolate),
        Menu("스팀 우유", STARBUCKS, 0, R.drawable.starbucks_steamed_milk),
        Menu("아이스 라임 플로터", STARBUCKS, 7, R.drawable.starbucks_iced_line_floater),
        Menu("아이스 오렌지 플로터", STARBUCKS, 7, R.drawable.starbucks_iced_organic_floater),
        Menu("아이스 와일드 베리 플로터", STARBUCKS, 7, R.drawable.starbucks_iced_wild_berry_floater),
        Menu("아이스 제주 까망 라떼", STARBUCKS, 0, R.drawable.starbucks_iced_jeju_black_sesame_latte),
        Menu("아이스 제주 노랑 고구마 라떼", STARBUCKS, 7, R.drawable.starbucks_iced_jeju_yellow_sweet_potato_latte),
        Menu("우유", STARBUCKS, 0, R.drawable.starbucks_milk),
        Menu("제주 까망 라떼", STARBUCKS, 0, R.drawable.starbucks_jeju_black_sesame_latte),
        Menu("제주 노랑 고구마 라떼", STARBUCKS, 0, R.drawable.starbucks_jeju_yellow_sweet_potato_latte),
        Menu("제주 쑥쑥 라떼", STARBUCKS, 0, R.drawable.starbucks_jeju_mugwork_latte),
        Menu("레드 선라이즈 270ML", STARBUCKS, 0, R.drawable.starbucks_red_sunrise_270),
        Menu("옐로우 패션 270ML", STARBUCKS, 0, R.drawable.starbucks_yellow_passion_270),
        Menu("파인 그린 270ML", STARBUCKS, 0, R.drawable.starbucks_fine_green_270),
        Menu("딸기 주스 190ML", STARBUCKS, 0, R.drawable.starbucks_strawberry_juice_190),
        Menu("망고 주스 190ML", STARBUCKS, 0, R.drawable.starbucks_mango_juice_190),
        Menu("수박 주스 190ML", STARBUCKS, 0, R.drawable.starbucks_watermelon_juice_190),
        Menu("케일&사과 주스 190ML", STARBUCKS, 0, R.drawable.starbucks_kale_apple_juice_190),
        Menu("토마토 주스 190ML", STARBUCKS, 0, R.drawable.starbucks_tomato_juice_190),
        Menu("한라봉 주스 190ML", STARBUCKS, 0, R.drawable.starbucks_hallabong_juice_190),
        Menu("블루베리 요거트 190ML", STARBUCKS, 0, R.drawable.starbucks_blueberr_yogurt_190),
        Menu("치아씨드 요거트 190ML", STARBUCKS, 0, R.drawable.starbucks_chiaseed_yorgurt_190)

    )

    fun ediya(): ArrayList<Menu> = arrayListOf(
        Menu("이디야", EDIYA, 245, R.drawable.starbucks_nitro_chocolat)
    )

    fun hollys(): ArrayList<Menu> = arrayListOf(
        Menu("에스프레소", HOLLYS, 76, R.drawable.hollys_espresso),
        Menu("에스프레소 마끼야또", HOLLYS, 76, R.drawable.hollys_espresso_macchiato),
        Menu("에스프레소 콘 파나", HOLLYS, 76, R.drawable.hollys_espresso_con_panna),
        Menu("아메리카노", HOLLYS, 152, R.drawable.hollys_americano),
        Menu("카페 라떼", HOLLYS, 152, R.drawable.hollys_caffe_latte),
        Menu("카푸치노", HOLLYS, 152, R.drawable.hollys_cappuccino),
        Menu("카페 모카", HOLLYS, 160, R.drawable.hollys_caffe_mocha),
        Menu("카라멜 마끼야또", HOLLYS, 152, R.drawable.hollys_caramel_macchiato),
        Menu("바닐라 딜라이트", HOLLYS, 93, R.drawable.hollys_vanilla_delight),
        Menu("리얼 벨지안 카페모", HOLLYS, 193, R.drawable.hollys_real_belgian_mocha),
        Menu("아포가토", HOLLYS, 129, R.drawable.hollys_affogato),
        Menu("콜드 브루", HOLLYS, 148, R.drawable.hollys_cold_brew),
        Menu("콜드 브루 라떼", HOLLYS, 148, R.drawable.hollys_cold_brew_latte),

        // 여기서부터
        Menu("리얼 벨지안 카페모", HOLLYS, 193, R.drawable.hollys_real_belgian_mocha),
        Menu("아포가토", HOLLYS, 129, R.drawable.hollys_affogato)

    )

    fun angelinus(): ArrayList<Menu> = arrayListOf(
        Menu("엔젤리너스", ANGELINUS, 245, R.drawable.starbucks_nitro_chocolat)
    )
}