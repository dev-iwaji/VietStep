package com.example.vocabapp.data.source

data class GrammarItem(
    val vietnamese: String,
    val japanese: String,
    val theme: String,
    val pattern: String,
    val memo: String
)

val grammarList = listOf(

    //
    // ✅ 基本形
    //
    GrammarItem(
        "Tôi ăn sáng lúc 7 giờ.",
        "私は7時に朝ごはんを食べます",
        "基本形",
        "主語 + 動詞",
        "～は…する"
    ),
    GrammarItem(
        "Anh ấy nghe nhạc mỗi tối.",
        "彼は毎晩音楽を聞きます",
        "基本形",
        "主語 + 動詞",
        "～は…する"
    ),
    GrammarItem(
        "Tôi đi làm mỗi ngày.",
        "私は毎日仕事に行きます",
        "基本形",
        "主語 + 動詞",
        "～は…する"
    ),
    GrammarItem(
        "Tôi không ăn hôm nay.",
        "私は今日食べません",
        "基本形",
        "主語 + không + 動詞",
        "～は…しない"
    ),
    GrammarItem(
        "Tôi không uống nước tối nay.",
        "私は今夜水を飲みません",
        "基本形",
        "主語 + không + 動詞",
        "～は…しない"
    ),
    GrammarItem(
        "Tôi không đi hôm nay.",
        "私は今日は行きません",
        "基本形",
        "主語 + không + 動詞",
        "～は…しない"
    ),
    GrammarItem(
        "Bạn có đi làm hôm nay không?",
        "あなたは今日仕事に行きますか？",
        "基本形",
        "主語 + có + 動詞 + không?",
        "～は…しますか？"
    ),
    GrammarItem(
        "Anh ấy có đọc sách này không?",
        "彼はこの本を読みますか？",
        "基本形",
        "主語 + có + 動詞 + không?",
        "～は…しますか？"
    ),
    GrammarItem(
        "Chị ấy có nấu món Việt không?",
        "彼女はベトナム料理を作りますか？",
        "基本形",
        "主語 + có + 動詞 + không?",
        "～は…しますか？"
    ),
    GrammarItem(
        "Tôi mệt sau khi làm việc.",
        "私は仕事の後で疲れています",
        "基本形",
        "主語 + 形容詞",
        "～は…です"
    ),
    GrammarItem(
        "Anh ấy bận hôm nay.",
        "彼は今日は忙しいです",
        "基本形",
        "主語 + 形容詞",
        "～は…です"
    ),
    GrammarItem(
        "Tôi rất vui hôm nay.",
        "私は今日はとても楽しいです",
        "基本形",
        "主語 + 形容詞",
        "～は…です"
    ),
    GrammarItem(
        "Tôi không mệt.",
        "私は疲れていません",
        "基本形",
        "主語 + không + 形容詞",
        "～は…ではありません"
    ),
    GrammarItem(
        "Anh ấy không bận hôm nay.",
        "彼は今日は忙しくありません",
        "基本形",
        "主語 + không + 形容詞",
        "～は…ではありません"
    ),
    GrammarItem(
        "Nhà này không lớn.",
        "この家は大きくないです",
        "基本形",
        "主語 + không + 形容詞",
        "～は…ではありません"
    ),
    GrammarItem(
        "Bạn có mệt không?",
        "あなたは疲れていますか？",
        "基本形",
        "主語 + có + 形容詞 + không?",
        "～は…ですか？"
    ),
    GrammarItem(
        "Anh ấy có bận hôm nay không?",
        "彼は今日は忙しいですか？",
        "基本形",
        "主語 + có + 形容詞 + không?",
        "～は…ですか？"
    ),
    GrammarItem(
        "Công việc hôm nay có khó không?",
        "今日の仕事は難しいですか？",
        "基本形",
        "主語 + có + 形容詞 + không?",
        "～は…ですか？"
    ),
    GrammarItem(
        "Đây là nhà của tôi.",
        "これは私の家です",
        "基本形",
        "đây là ~",
        "これは〜です"
    ),
    GrammarItem(
        "Đây là bạn của tôi.",
        "こちらは私の友達です",
        "基本形",
        "đây là ~",
        "これは〜です"
    ),
    GrammarItem(
        "Đây là đường đến sân bay.",
        "これは空港へ行く道です",
        "基本形",
        "đây là ~",
        "これは〜です"
    ),
    GrammarItem(
        "Đây không phải là sách của tôi.",
        "これは私の本ではありません",
        "基本形",
        "đây không phải là ~",
        "これは〜ではありません"
    ),
    GrammarItem(
        "Đây không phải là chỗ anh ấy chơi.",
        "ここは彼が遊ぶ場所ではありません",
        "基本形",
        "đây không phải là ~",
        "これは〜ではありません"
    ),
    GrammarItem(
        "Đây không phải là bàn trong phòng.",
        "これは部屋の机ではありません",
        "基本形",
        "đây không phải là ~",
        "これは〜ではありません"
    ),
    GrammarItem(
        "Đây có phải là nước không?",
        "これは水ですか？",
        "基本形",
        "đây có phải là ~ không?",
        "これは〜ですか？"
    ),
    GrammarItem(
        "Đây có phải là công ty của bạn không?",
        "これはあなたの会社ですか？",
        "基本形",
        "đây có phải là ~ không?",
        "これは〜ですか？"
    ),
    GrammarItem(
        "Đây có phải là máy tính mới không?",
        "これは新しいパソコンですか？",
        "基本形",
        "đây có phải là ~ không?",
        "これは〜ですか？"
    ),
    GrammarItem(
        "Tôi là người Nhật.",
        "私は日本人です",
        "基本形",
        "主語 + là ~",
        "…は～です"
    ),
    GrammarItem(
        "Anh ấy là người thích đọc sách.",
        "彼は本を読むのが好きな人です",
        "基本形",
        "主語 + là ~",
        "…は～です"
    ),
    GrammarItem(
        "Mùa hè là thời tiết rất nóng.",
        "夏はとても暑い季節です",
        "基本形",
        "主語 + là ~",
        "…は～です"
    ),
    GrammarItem(
        "Tôi không phải là sinh viên.",
        "私は大学生ではありません",
        "基本形",
        "主語 + không phải là ~",
        "…は～ではありません"
    ),
    GrammarItem(
        "Chị ấy không phải là giáo viên.",
        "彼女は先生ではありません",
        "基本形",
        "主語 + không phải là ~",
        "…は～ではありません"
    ),
    GrammarItem(
        "Tôi không phải là người Việt.",
        "私はベトナム人ではありません",
        "基本形",
        "主語 + không phải là ~",
        "…は～ではありません"
    ),
    GrammarItem(
        "Bạn có phải là người Nhật không?",
        "あなたは日本人ですか？",
        "基本形",
        "主語 + có phải là ~ không?",
        "…は～ですか？"
    ),
    GrammarItem(
        "Bạn có phải là sinh viên không?",
        "あなたは大学生ですか？",
        "基本形",
        "主語 + có phải là ~ không?",
        "…は～ですか？"
    ),
    GrammarItem(
        "Bạn có phải là bác sĩ không?",
        "あなたは医者ですか？",
        "基本形",
        "主語 + có phải là ~ không?",
        "…は～ですか？"
    ),
    GrammarItem(
        "Tôi có thời gian hôm nay.",
        "私は今日は時間があります",
        "基本形",
        "có + 名詞",
        "〜があります"
    ),
    GrammarItem(
        "Ở đây có nhà hàng.",
        "ここにレストランがあります",
        "基本形",
        "có + 名詞",
        "〜があります"
    ),
    GrammarItem(
        "Tôi có việc hôm nay.",
        "私は今日用事があります",
        "基本形",
        "có + 名詞",
        "〜があります"
    ),
    GrammarItem(
        "Trong phòng không có bàn.",
        "部屋には机がありません",
        "基本形",
        "không có + 名詞",
        "〜がありません"
    ),
    GrammarItem(
        "Hôm nay không có lớp học.",
        "今日は授業がありません",
        "基本形",
        "không có + 名詞",
        "〜がありません"
    ),
    GrammarItem(
        "Tôi không có tiền.",
        "私はお金がありません",
        "基本形",
        "không có + 名詞",
        "〜がありません"
    ),
    GrammarItem(
        "Bạn có thời gian không?",
        "あなたは時間ありますか？",
        "基本形",
        "có + 名詞 + không?",
        "〜がありますか？"
    ),
    GrammarItem(
        "Bạn có việc tối nay không?",
        "あなたは今夜予定ありますか？",
        "基本形",
        "có + 名詞 + không?",
        "〜がありますか？"
    ),
    GrammarItem(
        "Ở đó có nhiều người không?",
        "そこにはたくさんの人がいますか？",
        "基本形",
        "có + 名詞 + không?",
        "〜がありますか？"
    ),
    GrammarItem(
        "Tôi ở nhà.",
        "私は家にいます",
        "基本形",
        "人 + ở + 場所",
        "〜にいます"
    ),
    GrammarItem(
        "Anh ấy ở văn phòng.",
        "彼はオフィスにいます",
        "基本形",
        "人 + ở + 場所",
        "〜にいます"
    ),
    GrammarItem(
        "Chị ấy ở công ty.",
        "彼女は会社にいます",
        "基本形",
        "人 + ở + 場所",
        "〜にいます"
    ),
    GrammarItem(
        "Tôi không ở nhà.",
        "私は家にいません",
        "基本形",
        "人 + không ở + 場所",
        "〜にいません"
    ),
    GrammarItem(
        "Anh ấy không ở lớp.",
        "彼は教室にいません",
        "基本形",
        "人 + không ở + 場所",
        "〜にいません"
    ),
    GrammarItem(
        "Chị ấy không ở siêu thị.",
        "彼女はスーパーにいません",
        "基本形",
        "人 + không ở + 場所",
        "〜にいません"
    ),
    GrammarItem(
        "Bạn có ở nhà không?",
        "あなたは家にいますか？",
        "基本形",
        "人 + có ở + 場所 + không?",
        "〜にいますか？"
    ),
    GrammarItem(
        "Anh ấy có ở trường học không?",
        "彼は学校にいますか？",
        "基本形",
        "人 + có ở + 場所 + không?",
        "〜にいますか？"
    ),
    GrammarItem(
        "Anh ấy có ở sân bay không?",
        "彼は空港にいますか？",
        "基本形",
        "人 + có ở + 場所 + không?",
        "〜にいますか？"
    ),

    //
    // ✅ 連続動詞
    //
    GrammarItem(
        "Tôi đi ăn.",
        "私は食べに行きます",
        "連続動詞",
        "đi + 動詞",
        "〜しに行く"
    ),
    GrammarItem(
        "Anh ấy đi mua sách.",
        "彼は本を買いに行きます",
        "連続動詞",
        "đi + 動詞",
        "〜しに行く"
    ),
    GrammarItem(
        "Chúng tôi đi học.",
        "私たちは学校へ行きます",
        "連続動詞",
        "đi + 動詞",
        "〜しに行く"
    ),
    GrammarItem(
        "Anh ấy đến học tiếng Việt.",
        "彼はベトナム語を勉強しに来ます",
        "連続動詞",
        "đến + 動詞",
        "〜しに来る"
    ),
    GrammarItem(
        "Bạn đến chơi.",
        "あなたは遊びに来ます",
        "連続動詞",
        "đến + 動詞",
        "〜しに来る"
    ),
    GrammarItem(
        "Anh ấy đi mua sách.",
        "彼女は私に会いに来ます",
        "連続動詞",
        "đến + 動詞",
        "〜しに来る"
    ),
    GrammarItem(
        "Tôi về ngủ.",
        "私は帰って寝ます",
        "連続動詞",
        "về + 動詞",
        "帰って〜する"
    ),
    GrammarItem(
        "Tôi về học.",
        "私は帰って勉強します",
        "連続動詞",
        "về + 動詞",
        "帰って〜する"
    ),
    GrammarItem(
        "Anh ấy về ăn cơm.",
        "彼女は帰って食事をします",
        "連続動詞",
        "về + 動詞",
        "帰って〜する"
    ),
    GrammarItem(
        "Tôi bắt đầu học.",
        "私は勉強を始めます",
        "連続動詞",
        "bắt đầu + 動詞",
        "〜を始めます"
    ),
    GrammarItem(
        "Lớp bắt đầu học lúc 8 giờ.",
        "授業は8時に始まります",
        "連続動詞",
        "bắt đầu + 動詞",
        "〜を始めます"
    ),
    GrammarItem(
        "Anh ấy bắt đầu làm việc.",
        "彼は仕事を始めます",
        "連続動詞",
        "bắt đầu + 動詞",
        "〜を始めます"
    ),
    GrammarItem(
        "Tôi tiếp tục học.",
        "私は勉強を続けます",
        "連続動詞",
        "tiếp tục + 動詞",
        "〜を続けます"
    ),
    GrammarItem(
        "Tôi tiếp tục đọc sách.",
        "私は読書を続けます",
        "連続動詞",
        "tiếp tục + 動詞",
        "〜を続けます"
    ),
    GrammarItem(
        "Chúng tôi tiếp tục đi.",
        "私たちは進み続けます",
        "連続動詞",
        "tiếp tục + 動詞",
        "〜を続けます"
    ),
    GrammarItem(
        "Tôi kết thúc học lúc 5 giờ.",
        "私は5時に勉強を終えます",
        "連続動詞",
        "kết thúc + 動詞",
        "〜を終えます"
    ),
    GrammarItem(
        "Anh ấy kết thúc làm việc.",
        "彼は仕事を終えます",
        "連続動詞",
        "kết thúc + 動詞",
        "〜を終えます"
    ),
    GrammarItem(
        "Chúng tôi kết thúc cuộc họp.",
        "私たちは会議を終えます",
        "連続動詞",
        "kết thúc + 動詞",
        "〜を終えます"
    ),

    //
    // ✅ 疑問詞
    //
    GrammarItem(
        "Bạn làm gì?",
        "あなたは何をしていますか？",
        "疑問詞",
        "〜 gì?",
        "〜は何を？"
    ),
    GrammarItem(
        "Bạn đang ăn gì?",
        "あなたは何を食べていますか？",
        "疑問詞",
        "〜 gì?",
        "〜は何を？"
    ),
    GrammarItem(
        "Bạn muốn uống gì?",
        "あなたは何を飲みたいですか？",
        "疑問詞",
        "〜 gì?",
        "〜は何を？"
    ),
    GrammarItem(
        "Nhà vệ sinh ở đâu?",
        "トイレはどこですか？",
        "疑問詞",
        "〜 ở đâu?",
        "〜はどこ？"
    ),
    GrammarItem(
        "Anh ấy làm việc ở đâu?",
        "彼はどこで働いていますか？",
        "疑問詞",
        "〜 ở đâu?",
        "〜はどこ？"
    ),
    GrammarItem(
        "Chị ấy mua sách ở đâu?",
        "彼女はどこで本を買いましたか？",
        "疑問詞",
        "〜 ở đâu?",
        "〜はどこ？"
    ),
    GrammarItem(
        "Anh ấy là ai?",
        "彼は誰ですか",
        "疑問詞",
        "〜 là ai?",
        "～は誰ですか？"
    ),
    GrammarItem(
        "Chị ấy giáo đó là ai?",
        "あの先生は誰ですか？",
        "疑問詞",
        "〜 là ai?",
        "～は誰ですか？"
    ),
    GrammarItem(
        "Người đang gọi điện là ai?",
        "電話している人は誰ですか？",
        "疑問詞",
        "〜 là ai?",
        "～は誰ですか？"
    ),
    GrammarItem(
        "Ai đang gọi bạn?",
        "誰があなたに電話していますか？",
        "疑問詞",
        "Ai 〜?",
        "誰が〜しますか？"
    ),
    GrammarItem(
        "Ai giúp bạn?",
        "誰があなたを手伝いますか？",
        "疑問詞",
        "Ai 〜?",
        "誰が〜しますか？"
    ),
    GrammarItem(
        "Ai đang chờ bạn?",
        "誰があなたを待っていますか？",
        "疑問詞",
        "Ai 〜?",
        "誰が〜しますか？"
    ),
    GrammarItem(
        "Bao giờ bạn sẽ đi du lịch?",
        "あなたはいつ旅行に行きますか？",
        "疑問詞",
        "bao giờ 〜?",
        "いつ〜しますか？"
    ),
    GrammarItem(
        "Bạn bao giờ đến sân bay?",
        "あなたはいつ空港に来ますか？",
        "疑問詞",
        "bao giờ 〜?",
        "いつ〜しますか？"
    ),
    GrammarItem(
        "Bạn bao giờ ăn?",
        "あなたはいつ食べますか？",
        "疑問詞",
        "bao giờ 〜?",
        "いつ〜しますか？"
    ),
    GrammarItem(
        "Khi nào bạn sẽ đến văn phòng?",
        "あなたはいつオフィスに来ますか？",
        "疑問詞",
        "khi nào〜?",
        "いつ〜しますか？"
    ),
    GrammarItem(
        "Khi nào bạn gặp tôi?",
        "あなたはいつ私と会いますか？",
        "疑問詞",
        "khi nào〜?",
        "いつ〜しますか？"
    ),
    GrammarItem(
        "Khi nào món ăn đến?",
        "料理はいつ来ますか？",
        "疑問詞",
        "khi nào〜?",
        "いつ〜しますか？"
    ),
    GrammarItem(
        "Bạn đi Đà Nẵng bao giờ?",
        "あなたはいつダナンへ行きましたか？",
        "疑問詞",
        "〜 bao giờ?",
        "いつ〜しましたか？"
    ),
    GrammarItem(
        "Anh ấy ngủ dậy bao giờ?",
        "彼はいつ起きましたか？",
        "疑問詞",
        "〜 bao giờ?",
        "いつ〜しましたか？"
    ),
    GrammarItem(
        "Chị ấy nấu món đó bao giờ?",
        "彼女はいつその料理を作りましたか？",
        "疑問詞",
        "〜 bao giờ?",
        "いつ〜しましたか？"
    ),
    GrammarItem(
        "Bạn đến đây khi nào?",
        "あなたはいつここに来ましたか？",
        "疑問詞",
        "〜 khi nào?",
        "いつ〜しましたか？"
    ),
    GrammarItem(
        "Bạn đã gọi điện thoại khi nào?",
        "あなたはいつ電話しましたか？",
        "疑問詞",
        "〜 khi nào?",
        "いつ〜しましたか？"
    ),
    GrammarItem(
        "Chị ấy đọc sách đó khi nào?",
        "彼女はいつその本を読みましたか？",
        "疑問詞",
        "〜 khi nào?",
        "いつ〜しましたか？"
    ),
    GrammarItem(
        "Tại sao bạn nghỉ hôm nay?",
        "あなたはなぜ今日は休みなんですか？",
        "疑問詞",
        "tại sao 〜 ?",
        "なぜ？"
    ),
    GrammarItem(
        "Tại sao bạn không đi?",
        "あなたはなぜ行かないのですか？",
        "疑問詞",
        "tại sao 〜 ?",
        "なぜ？"
    ),
    GrammarItem(
        "Tại sao bạn đến muộn?",
        "あなたはなぜ遅れましたか？",
        "疑問詞",
        "tại sao 〜 ?",
        "なぜ？"
    ),
    GrammarItem(
        "Vì sao bạn học tiếng Việt?",
        "なぜあなたはベトナム語を勉強するのですか？",
        "疑問詞",
        "vì sao 〜 ?",
        "なぜ？"
    ),
    GrammarItem(
        "Vì sao bạn thích Việt Nam?",
        "なぜあなたはベトナムが好きなのですか？",
        "疑問詞",
        "vì sao 〜 ?",
        "なぜ？"
    ),
    GrammarItem(
        "Vì sao bạn không ăn?",
        "なぜあなたは食べないのですか？",
        "疑問詞",
        "vì sao 〜 ?",
        "なぜ？"
    ),
    GrammarItem(
        "Cái này bao nhiêu tiền?",
        "これはいくらですか？",
        "疑問詞",
        "bao nhiêu tiền?",
        "いくらですか？（値段）"
    ),
    GrammarItem(
        "Cà phê bao nhiêu tiền?",
        "コーヒーはいくらですか？",
        "疑問詞",
        "bao nhiêu tiền?",
        "いくらですか？（値段）"
    ),
    GrammarItem(
        "Bữa ăn này bao nhiêu tiền?",
        "この食事はいくらですか？",
        "疑問詞",
        "bao nhiêu tiền?",
        "いくらですか？（値段）"
    ),
    GrammarItem(
        "Bạn có bao nhiêu sách?",
        "あなたは本何冊持っていますか？",
        "疑問詞",
        "bao nhiêu + 名詞?",
        "どれくらいですか？（数量）"
    ),
    GrammarItem(
        "Có bao nhiêu người?",
        "何人いますか？",
        "疑問詞",
        "bao nhiêu + 名詞?",
        "どれくらいですか？（数量）"
    ),
    GrammarItem(
        "Bạn mua bao nhiêu cái?",
        "あなたはいくつ買いましたか？",
        "疑問詞",
        "bao nhiêu + 名詞?",
        "どれくらいですか？（数量）"
    ),
    GrammarItem(
        "Bạn ở Nhật bao lâu?",
        "あなたは日本にどのくらいいますか？",
        "疑問詞",
        "bao lâu?",
        "どのくらいですか？（時間）"
    ),
    GrammarItem(
        "Tôi đợi bao lâu?",
        "私はどのくらい待ちますか？",
        "疑問詞",
        "bao lâu?",
        "どのくらいですか？（時間）"
    ),
    GrammarItem(
        "Bạn ở đây được bao lâu rồi?",
        "あなたはここにどのくらいいますか？",
        "疑問詞",
        "bao lâu?",
        "どのくらいですか？（時間）"
    ),
    GrammarItem(
        "Bạn ngủ bao nhiêu giờ?",
        "あなたは何時間寝ますか？",
        "疑問詞",
        "bao nhiêu tiền?",
        "どれくらいですか？（時間）"
    ),
    GrammarItem(
        "Chị ấy học bao nhiêu giờ?",
        "彼女は何時間勉強しますか？",
        "疑問詞",
        "bao nhiêu tiền?",
        "どれくらいですか？（時間）"
    ),
    GrammarItem(
        "Bạn àm việc bao nhiêu giờ?",
        "あなたは何時間働いていますか？",
        "疑問詞",
        "bao nhiêu tiền?",
        "どれくらいですか？（時間）"
    ),
    GrammarItem(
        "Trà này nóng bao nhiêu?",
        "このお茶はどれくらい熱いですか？",
        "疑問詞",
        "形容詞 + bao nhiêu?",
        "どれくらい〜ですか？"
    ),
    GrammarItem(
        "Ở đây lạnh bao nhiêu?",
        "ここはどれくらい寒いですか？",
        "疑問詞",
        "形容詞 + bao nhiêu?",
        "どれくらい〜ですか？"
    ),
    GrammarItem(
        "Anh ấy cao bao nhiêu?",
        "彼の身長はどれくらいですか？",
        "疑問詞",
        "形容詞 + bao nhiêu?",
        "どれくらい〜ですか？"
    ),
    GrammarItem(
        "Bạn bao nhiêu tuổi?",
        "あなたは何歳ですか？",
        "疑問詞",
        "bao nhiêu + 期間?",
        "どれくらいですか？（年齢・期間）"
    ),
    GrammarItem(
        "Bạn làm bao nhiêu năm?",
        "あなたは何年働いていますか？",
        "疑問詞",
        "bao nhiêu + 期間?",
        "どれくらいですか？（年齢・期間）"
    ),
    GrammarItem(
        "Một năm có bao nhiêu tuần?",
        "一年には何週間ありますか？",
        "疑問詞",
        "bao nhiêu + 期間?",
        "どれくらいですか？（年齢・期間）"
    ),
    GrammarItem(
        "Bạn nấu ăn như thế nào?",
        "あなたはどうやって料理しますか？",
        "疑問詞",
        "動詞 + như thế nào??",
        "どうやって〜しますか？？"
    ),
    GrammarItem(
        "Bạn học tiếng Việt như thế nào?",
        "あなたはどうやってベトナム語を勉強しますか？",
        "疑問詞",
        "動詞 + như thế nào??",
        "どうやって〜しますか？"
    ),
    GrammarItem(
        "Bạn đi đến sân bay như thế nào?",
        "あなたはどうやって空港に行きますか？",
        "疑問詞",
        "動詞 + như thế nào??",
        "どうやって〜しますか？"
    ),
    GrammarItem(
        "Bạn muốn sách nào?",
        "どの本が欲しいですか？",
        "疑問詞",
        "名詞 + nào?",
        "どの〜？"
    ),
    GrammarItem(
        "Anh ấy thích áo nào?",
        "彼はどの服が好きですか？",
        "疑問詞",
        "名詞 + nào?",
        "どの〜？"
    ),
    GrammarItem(
        "Chị ấy chọn túi nào?",
        "彼女はどのバッグを選びますか？",
        "疑問詞",
        "名詞 + nào?",
        "どの〜？"
    ),
    GrammarItem(
        "Bạn muốn đến nhà hàng nào?",
        "どのレストランに行きたいですか？",
        "疑問詞",
        "場所 + nào?",
        "どの場所？"
    ),
    GrammarItem(
        "Anh ấy đang ở phòng nào?",
        "彼はどの部屋にいますか？",
        "疑問詞",
        "場所 + nào?",
        "どの場所？"
    ),
    GrammarItem(
        "Chị ấy chọn quán nào để ăn trưa?",
        "彼女は昼ごはんにどの店を選びますか？",
        "疑問詞",
        "場所 + nào?",
        "どの場所？"
    ),
    GrammarItem(
        "Bạn đang nói về người nào?",
        "あなたはどの人のことを話していますか？",
        "疑問詞",
        "人 + nào?",
        "どの人？"
    ),
    GrammarItem(
        "Anh ấy muốn gặp ai nào?",
        "彼はどの人に会いたいですか？",
        "疑問詞",
        "人 + nào?",
        "どの人？"
    ),
    GrammarItem(
        "Bạn thích người nào?",
        "あなたはどの人が好きですか？",
        "疑問詞",
        "人 + nào?",
        "どの人？"
    ),
    GrammarItem(
        "Bạn làm thế nào để đi?",
        "どうやって行きますか？",
        "疑問詞",
        "làm thế nào",
        "どのように？"
    ),
    GrammarItem(
        "Bạn làm thế nào để học?",
        "どうやって勉強しますか？",
        "疑問詞",
        "làm thế nào",
        "どのように？"
    ),
    GrammarItem(
        "Bạn làm thế nào để nấu ăn?",
        "どうやって料理しますか？",
        "疑問詞",
        "làm thế nào",
        "どのように？"
    ),
    GrammarItem(
        "Bạn có mấy anh chị em vậy?",
        "兄弟姉妹は何人いますか？",
        "疑問詞",
        "mấy + 名詞",
        "いくつ？"
    ),
    GrammarItem(
        "Anh ấy muốn mua mấy cái áo?",
        "彼は服を何枚買いたいですか？",
        "疑問詞",
        "mấy + 名詞",
        "いくつ？"
    ),
    GrammarItem(
        "Bạn có mấy người bạn?",
        "友達は何人いますか？",
        "疑問詞",
        "mấy + 名詞",
        "いくつ？"
    ),

    //
    // ✅ 時制
    //
    GrammarItem(
        "Tôi đã ăn sáng.",
        "私は朝ごはんを食べた",
        "時制",
        "đã + 動詞",
        "〜した"
    ),
    GrammarItem(
        "Chị ấy đã uống cà phê.",
        "彼女はコーヒーを飲んだ",
        "時制",
        "đã + 動詞",
        "〜した"
    ),
    GrammarItem(
        "Anh ấy đã xem phim.",
        "彼は映画を見た",
        "時制",
        "đã + 動詞",
        "〜した"
    ),
    GrammarItem(
        "Tôi vừa ăn xong.",
        "ちょうど食べ終わったところです",
        "時制",
        "vừa + 動詞",
        "ちょうど〜したところ"
    ),
    GrammarItem(
        "Anh ấy vừa đến đây.",
        "彼はちょうどここに来たところです",
        "時制",
        "vừa + 動詞",
        "ちょうど〜したところ"
    ),
    GrammarItem(
        "Tôi vừa mở cửa.",
        "私はちょうどドアを開けたところです",
        "時制",
        "vừa + 動詞",
        "ちょうど〜したところ"
    ),
    GrammarItem(
        "Tôi mới mua điện thoại.",
        "私はスマホを買ったばかりです",
        "時制",
        "mới + 動詞",
        "〜したばかり"
    ),
    GrammarItem(
        "Anh ấy mới đến nhà.",
        "彼は家に来たばかりです",
        "時制",
        "mới + 動詞",
        "〜したばかり"
    ),
    GrammarItem(
        "Tôi mới mua sách.",
        "私は本を買ったばかりです",
        "時制",
        "mới + 動詞",
        "〜したばかり"
    ),
    GrammarItem(
        "Tôi đang làm việc.",
        "私は仕事をしています",
        "時制",
        "đang + 動詞",
        "〜している"
    ),
    GrammarItem(
        "Tôi đang học tiếng Việt.",
        "私はベトナム語を勉強しています",
        "時制",
        "đang + 動詞",
        "〜している"
    ),
    GrammarItem(
        "Tôi đang ăn.",
        "私は食事をしています",
        "時制",
        "đang + 動詞",
        "〜している"
    ),
    GrammarItem(
        "Tôi sắp đi.",
        "私はもうすぐ行きます",
        "時制",
        "sắp + 動詞",
        "もうすぐ〜する"
    ),
    GrammarItem(
        "Chị ấy sắp nấu ăn.",
        "彼女はもうすぐ料理します",
        "時制",
        "sắp + 動詞",
        "もうすぐ〜する"
    ),
    GrammarItem(
        "Tôi sắp ngủ.",
        "私はもうすぐ寝ます",
        "時制",
        "sắp + 動詞",
        "もうすぐ〜する"
    ),
    GrammarItem(
        "Tôi sẽ gọi bạn.",
        "私はあなたに電話します",
        "時制",
        "sẽ + 動詞",
        "〜する"
    ),
    GrammarItem(
        "Tôi sẽ đi sau.",
        "私は後で行きます",
        "時制",
        "sẽ + 動詞",
        "〜する"
    ),
    GrammarItem(
        "Tôi sẽ làm ngay.",
        "私はすぐにやります",
        "時制",
        "sẽ + 動詞",
        "〜する"
    ),
    GrammarItem(
        "Tôi đã từng đến Hà Nội.",
        "私はハノイへ行ったことがあります",
        "時制",
        "đã từng + 動詞",
        "〜したことがある"
    ),
    GrammarItem(
        "Chị ấy đã từng gặp tôi.",
        "彼女は私に会ったことがあります",
        "時制",
        "đã từng + 動詞",
        "〜したことがある"
    ),
    GrammarItem(
        "Anh ấy đã từng học tiếng Việt.",
        "彼はベトナム語を勉強したことがあります",
        "時制",
        "đã từng + 動詞",
        "〜したことがある"
    ),
    GrammarItem(
        "Tôi ăn rồi",
        "私はもう食べました",
        "時制",
        "動詞 + rồi",
        "〜してしまった"
    ),
    GrammarItem(
        "Anh ấy đi rồi.",
        "彼はもう行きました",
        "時制",
        "動詞 + rồi",
        "〜してしまった"
    ),
    GrammarItem(
        "Tôi đọc sách rồi.",
        "私はもう本を読みました",
        "時制",
        "動詞 + rồi",
        "〜してしまった"
    ),
    GrammarItem(
        "Tôi ăn xong.",
        "私は食べ終えます",
        "頻度",
        "動詞 + xong",
        "〜し終える"
    ),
    GrammarItem(
        "Chị ấy viết xong thư.",
        "彼女は手紙を書き終えます",
        "頻度",
        "動詞 + xong",
        "〜し終える"
    ),
    GrammarItem(
        "Anh ấy làm xong công việc.",
        "彼は仕事を終えます",
        "頻度",
        "動詞 + xong",
        "〜し終える"
    ),
    GrammarItem(
        "Tôi ăn xong rồi.",
        "私はもう食べ終えました",
        "時制",
        "動詞 + xong rồi",
        "もう～し終えた"
    ),
    GrammarItem(
        "Bạn đọc xong rồi.",
        "あなたはもう読み終えました",
        "時制",
        "動詞 + xong rồi",
        "もう～し終えた"
    ),
    GrammarItem(
        "Chúng tôi học xong rồi.",
        "私たちはもう勉強を終えました",
        "時制",
        "動詞 + xong rồi",
        "もう～し終えた"
    ),
    GrammarItem(
        "Tôi chưa làm công việc.",
        "私はまだ仕事をしていません",
        "時制",
        "chưa + 動詞",
        "まだ〜していない"
    ),
    GrammarItem(
        "Tôi chưa nhìn ảnh.",
        "私はまだ写真を見ていません",
        "時制",
        "chưa + 動詞",
        "まだ〜していない"
    ),
    GrammarItem(
        "Chị ấy chưa nói.",
        "彼女はまだ話していません",
        "時制",
        "chưa + 動詞",
        "まだ〜していない"
    ),
    GrammarItem(
        "Bạn gọi xong chưa?",
        "あなたはもう電話しましたか？",
        "時制",
        "動詞 + xong chưa?",
        "もう〜し終えましたか？"
    ),
    GrammarItem(
        "Bạn trả xong chưa?",
        "あなたはもう支払い終えましたか？",
        "時制",
        "動詞 + xong chưa?",
        "もう〜し終えましたか？"
    ),
    GrammarItem(
        "Chị ấy nấu xong chưa?",
        "彼女はもう料理し終えましたか？",
        "時制",
        "動詞 + xong chưa?",
        "もう〜し終えましたか？"
    ),
    GrammarItem(
        "Bạn đã ăn chưa?",
        "あなたはもう食べましたか？",
        "時制",
        "đã + 動詞 + chưa?",
        "もう〜しましたか？"
    ),
    GrammarItem(
        "Anh ấy đã đọc sách này chưa?",
        "彼はもうこの本を読みましたか？",
        "時制",
        "đã + 動詞 + chưa?",
        "もう〜しましたか？"
    ),
    GrammarItem(
        "Chị ấy đã đi làm chưa?",
        "彼女はもう仕事に行きましたか？",
        "時制",
        "đã + 動詞 + chưa?",
        "もう〜しましたか？"
    ),
    GrammarItem(
        "Bạn sắp về chưa?",
        "あなたはもうすぐ帰りますか？",
        "時制",
        "sắp + 動詞 + chưa?",
        "もうすぐ〜しますか？"
    ),
    GrammarItem(
        "Chị ấy sắp đến chưa?",
        "彼女はもうすぐ来ますか？",
        "時制",
        "sắp + 動詞 + chưa?",
        "もうすぐ〜しますか？"
    ),
    GrammarItem(
        "Bạn sắp gặp anh ấy chưa?",
        "あなたはもうすぐ彼に会いますか？",
        "時制",
        "sắp + 動詞 + chưa?",
        "もうすぐ〜しますか？"
    ),

    //
    // ✅ 頻度
    //
    GrammarItem(
        "Bạn luôn luôn lái xe máy.",
        "あなたはいつもバイクを運転します",
        "頻度",
        "luôn luôn + 動詞",
        "いつも〜します"
    ),
    GrammarItem(
        "Anh ấy luôn luôn đến đúng giờ.",
        "彼はいつも時間どおりに来ます",
        "頻度",
        "luôn luôn + 動詞",
        "いつも〜します"
    ),
    GrammarItem(
        "Tôi luôn luôn học buổi sáng.",
        "私はいつも朝勉強します",
        "頻度",
        "luôn luôn + 動詞",
        "いつも〜します"
    ),
    GrammarItem(
        "Chị ấy thường đọc sách vào buổi tối.",
        "彼女はたいてい夜に本を読みます",
        "頻度",
        "thường + 動詞",
        "たいてい〜します"
    ),
    GrammarItem(
        "Anh ấy thường đi học bằng xe buýt.",
        "彼はたいていバスで学校へ行きます",
        "頻度",
        "thường + 動詞",
        "たいてい〜します"
    ),
    GrammarItem(
        "Tôi thường mua cà phê.",
        "私はたいていコーヒーを買います",
        "頻度",
        "thường + 動詞",
        "たいてい〜します"
    ),
    GrammarItem(
        "Tôi hay nghe nhạc.",
        "私はよく音楽を聴きます",
        "頻度",
        "hay + 動詞",
        "よく〜します"
    ),
    GrammarItem(
        "Anh ấy hay chơi bóng đá.",
        "彼はよくサッカーをします",
        "頻度",
        "hay + 動詞",
        "よく〜します"
    ),
    GrammarItem(
        "Tôi hay xem tivi.",
        "私はよくテレビを見ます",
        "頻度",
        "hay + 動詞",
        "よく〜します"
    ),
    GrammarItem(
        "Tôi đôi khi nấu ăn ở nhà.",
        "私は時々家で料理をします",
        "頻度",
        "đôi khi + 動詞",
        "時々〜します"
    ),
    GrammarItem(
        "Bạn đôi khi đi bộ.",
        "あなたは時々歩きます",
        "頻度",
        "đôi khi + 動詞",
        "時々〜します"
    ),
    GrammarItem(
        "Tôi đôi khi gặp bạn.",
        "私は時々あなたに会います",
        "頻度",
        "đôi khi + 動詞",
        "時々〜します"
    ),
    GrammarItem(
        "Tôi hiếm khi gọi điện thoại.",
        "私はめったに電話しません",
        "頻度",
        "hiếm khi + 動詞",
        "めったに〜ない"
    ),
    GrammarItem(
        "Tôi hiếm khi đi taxi.",
        "私はめったにタクシーに乗りません",
        "頻度",
        "hiếm khi + 動詞",
        "めったに〜ない"
    ),
    GrammarItem(
        "Chị ấy hiếm khi xem tivi.",
        "彼女はめったにテレビを見ません",
        "頻度",
        "hiếm khi + 動詞",
        "めったに〜ない"
    ),
    GrammarItem(
        "Tôi không bao giờ hút thuốc.",
        "私は決してたばこを吸いません",
        "頻度",
        "không bao giờ + 動詞",
        "決して〜ない"
    ),
    GrammarItem(
        "Anh ấy không bao giờ nói dối.",
        "彼は決して嘘を言いません",
        "頻度",
        "không bao giờ + 動詞",
        "決して〜ない"
    ),
    GrammarItem(
        "Chị ấy không bao giờ đến muộn.",
        "彼女は決して遅れて来ません",
        "頻度",
        "không bao giờ + 動詞",
        "決して〜ない"
    ),
    GrammarItem(
        "Tôi chưa bao giờ uống cà phê.",
        "私はコーヒーを一度も飲んだことがありません",
        "頻度",
        "chưa bao giờ + 動詞",
        "一度も〜したことがない"
    ),
    GrammarItem(
        "Chị ấy chưa bao giờ lái xe.",
        "彼女は一度も車を運転したことがありません",
        "頻度",
        "chưa bao giờ + 動詞",
        "一度も〜したことがない"
    ),
    GrammarItem(
        "Chúng tôi chưa bao giờ đọc cuốn sách này.",
        "私たちはこの本を一度も読んだことがありません",
        "頻度",
        "chưa bao giờ + 動詞",
        "一度も〜したことがない"
    ),
    GrammarItem(
        "Tôi không bao giờ hút thuốc.",
        "私は決してタバコを吸いません",
        "頻度",
        "không bao giờ + 動詞",
        "全く〜しない"
    ),
    GrammarItem(
        "Anh ấy không bao giờ đọc sách.",
        "彼は本を全然読みません",
        "頻度",
        "không bao giờ + 動詞",
        "全く〜しない"
    ),
    GrammarItem(
        "Tôi không bao giờ lái xe.",
        "私は車の運転全くしません",
        "頻度",
        "không bao giờ + 動詞",
        "全く〜しない"
    ),
    GrammarItem(
        "Tôi chẳng bao giờ uống cà phê.",
        "私はめったにコーヒーを飲みません",
        "頻度",
        "chẳng bao giờ + 動詞",
        "ほとんど〜しない"
    ),
    GrammarItem(
        "Anh ấy chẳng bao giờ đọc báo.",
        "彼はほとんど新聞を読みません",
        "頻度",
        "chẳng bao giờ + 動詞",
        "ほとんど〜しない"
    ),
    GrammarItem(
        "Tôi chẳng bao giờ nấu ăn ở nhà.",
        "私は家でほとんど料理しません",
        "頻度",
        "chẳng bao giờ + 動詞",
        "ほとんど〜しない"
    ),
    GrammarItem(
        "Bạn đừng bao giờ quên hẹn.",
        "あなたは絶対約束を忘れてはいけない",
        "頻度",
        "đừng bao giờ + 動詞",
        "絶対〜してはいけない"
    ),
    GrammarItem(
        "Bạn đừng bao giờ ngủ muộn.",
        "あなたは絶対夜更かししてはいけない",
        "頻度",
        "đừng bao giờ + 動詞",
        "絶対〜してはいけない"
    ),
    GrammarItem(
        "Bạn đừng bao giờ mở cửa lúc đêm.",
        "あなたは夜に絶対ドアを開けてはいけません",
        "頻度",
        "đừng bao giờ + 動詞",
        "絶対〜してはいけない"
    ),
    GrammarItem(
        "Bạn đã đi Đà Nẵng bao giờ chưa?",
        "あなたはダナンへ行ったことがありますか？",
        "頻度",
        "đã + 動詞句 + bao giờ chưa?",
        "今までに〜したことがありますか？"
    ),
    GrammarItem(
        "Bạn đã đọc sách này bao giờ chưa?",
        "あなたはこの本を読んだことがありますか？",
        "頻度",
        "đã + 動詞句 + bao giờ chưa?",
        "今までに〜したことがありますか？"
    ),
    GrammarItem(
        "Chị ấy đã nấu món Nhật bao giờ chưa?",
        "彼女は日本料理を作ったことがありますか？",
        "頻度",
        "đã + 動詞句 + bao giờ chưa?",
        "今までに〜したことがありますか？"
    ),

    //
    // ✅ 助動詞
    //
    GrammarItem(
        "Tôi phải đi nga.y",
        "私はすぐに行かなければなりません",
        "助動詞",
        "phải + 動詞",
        "〜しなければならない"
    ),
    GrammarItem(
        "Tôi phải làm việc hôm nay.",
        "私は今日働かなければなりません",
        "助動詞",
        "phải + 動詞",
        "〜しなければならない"
    ),
    GrammarItem(
        "Tôi phải trả lời ngay.",
        "私はすぐに返事しなければなりません",
        "助動詞",
        "phải + 動詞",
        "〜しなければならない"
    ),
    GrammarItem(
        "Bạn không phải đi hôm nay.",
        "今日は行かなくてよいです",
        "助動詞",
        "không phải + 動詞",
        "〜しなくてよい"
    ),
    GrammarItem(
        "Tôi không phải làm việc này.",
        "この仕事をする必要はありません",
        "助動詞",
        "không phải + 動詞",
        "〜しなくてよい"
    ),
    GrammarItem(
        "Chúng ta không phải chờ lâu.",
        "私たちは長く待つ必要はありません",
        "助動詞",
        "không phải + 動詞",
        "〜しなくてよい"
    ),
    GrammarItem(
        "Tôi phải đi không?",
        "私は行かなければなりませんか？",
        "助動詞",
        "phải + 動詞 + không?",
        "〜しなければならないですか？"
    ),
    GrammarItem(
        "Tôi phải học hôm nay không?",
        "今日は勉強しなければなりませんか？",
        "助動詞",
        "phải + 動詞 + không?",
        "〜しなければならないですか？"
    ),
    GrammarItem(
        "Chúng tôi phải chờ không?",
        "私たちは待たなければなりませんか？",
        "助動詞",
        "phải + 動詞 + không?",
        "〜しなければならないですか？"
    ),
    GrammarItem(
        "Tôi có thể giúp bạn.",
        "私はあなたを手伝うことができます",
        "助動詞",
        "có thể + 動詞",
        "〜できる"
    ),
    GrammarItem(
        "Tôi có thể đi hôm nay.",
        "私は今日行くことができます",
        "助動詞",
        "có thể + 動詞",
        "〜できる"
    ),
    GrammarItem(
        "Tôi có thể làm việc này.",
        "私はこの仕事ができます",
        "助動詞",
        "có thể + 動詞",
        "〜できる"
    ),
    GrammarItem(
        "Chị ấy không thể nấu ăn.",
        "彼女は料理できません",
        "助動詞",
        "không thể + 動詞",
        "〜できない"
    ),
    GrammarItem(
        "Anh ấy không thể lái xe.",
        "彼は運転できません",
        "助動詞",
        "không thể + 動詞",
        "〜できない"
    ),
    GrammarItem(
        "Tôi không thể hiểu.",
        "私は理解できません",
        "助動詞",
        "không thể + 動詞",
        "〜できない"
    ),
    GrammarItem(
        "Anh ấy có thể giúp tôi không?",
        "手伝ってもらえますか？",
        "助動詞",
        "có thể + 動詞 + không?",
        "〜してもらえますか？／〜できますか？"
    ),
    GrammarItem(
        "Chị ấy có thể nói chậm không?",
        "ゆっくり話してもらえますか？",
        "助動詞",
        "có thể + 動詞 + không?",
        "〜してもらえますか？／〜できますか？"
    ),
    GrammarItem(
        "Tôi có thể mở cửa không?",
        "ドアを開けてもらえますか？",
        "助動詞",
        "có thể + 動詞 + không?",
        "〜してもらえますか？／〜できますか？"
    ),
    GrammarItem(
        "Bạn nên nghỉ.",
        "あなたは休むべきです",
        "助動詞",
        "nên + 動詞",
        "〜すべき"
    ),
    GrammarItem(
        "Bạn nên đi bộ.",
        "あなたは歩くべきです",
        "助動詞",
        "nên + 動詞",
        "〜すべき"
    ),
    GrammarItem(
        "Tôi nên đi sớm.",
        "私は早く行くべきです",
        "助動詞",
        "nên + 動詞",
        "〜すべき"
    ),
    GrammarItem(
        "Bạn không nên thức khuya.",
        "あなたは夜更かししない方がいいです",
        "助動詞",
        "không nên + 動詞",
        "〜しない方がいい"
    ),
    GrammarItem(
        "Bạn không nên ăn nhiều.",
        "あなたは食べすぎない方がいいです",
        "助動詞",
        "không nên + 動詞",
        "〜しない方がいい"
    ),
    GrammarItem(
        "Anh ấy không nên hút thuốc.",
        "彼はたばこを吸わない方がいいです",
        "助動詞",
        "không nên + 動詞",
        "〜しない方がいい"
    ),
    GrammarItem(
        "Tôi nên đi không?",
        "私は行った方がいいですか？",
        "助動詞",
        "nên + 動詞 + không?",
        "〜した方がいいですか？"
    ),
    GrammarItem(
        "Tôi nên viết không?",
        "私は書いた方がいいですか？",
        "助動詞",
        "nên + 動詞 + không?",
        "〜した方がいいですか？"
    ),
    GrammarItem(
        "Chúng tôi nên đợi không?",
        "私たちは待った方がいいですか？",
        "助動詞",
        "nên + 動詞 + không?",
        "〜した方がいいですか？"
    ),
    GrammarItem(
        "Tôi muốn nghỉ hôm nay.",
        "私は今日は休みたいです",
        "助動詞",
        "muốn + 動詞",
        "〜したい"
    ),
    GrammarItem(
        "Tôi muốn hỏi một câu.",
        "私は質問をしたいです",
        "助動詞",
        "muốn + 動詞",
        "〜したい"
    ),
    GrammarItem(
        "Tôi muốn gặp bạn.",
        "私はあなたに会いたいです",
        "助動詞",
        "muốn + 動詞",
        "〜したい"
    ),
    GrammarItem(
        "Tôi không muốn đi.",
        "私は行きたくないです",
        "助動詞",
        "không muốn + 動詞",
        "〜したくない"
    ),
    GrammarItem(
        "Tôi không muốn ăn.",
        "私は食べたくないです",
        "助動詞",
        "không muốn + 動詞",
        "〜したくない"
    ),
    GrammarItem(
        "Tôi không muốn chọn.",
        "私は選びたくないです",
        "助動詞",
        "không muốn + 動詞",
        "〜したくない"
    ),
    GrammarItem(
        "Bạn có muốn uống cà phê không?",
        "あなたはコーヒーを飲みたいですか？",
        "助動詞",
        "có muốn + 動詞 + không?",
        "〜したいですか？"
    ),
    GrammarItem(
        "Bạn có muốn gặp không?",
        "あなたは会いたいですか？",
        "助動詞",
        "có muốn + 動詞 + không?",
        "〜したいですか？"
    ),
    GrammarItem(
        "Anh ấy có muốn xem phim không?",
        "彼は映画を見たいですか？",
        "助動詞",
        "có muốn + 動詞 + không?",
        "〜したいですか？"
    ),
    GrammarItem(
        "Tôi cần học.",
        "私は勉強する必要があります",
        "助動詞",
        "cần + 動詞",
        "〜が必要"
    ),
    GrammarItem(
        "Anh ấy cần gọi điện thoại.",
        "彼は電話する必要があります",
        "助動詞",
        "cần + 動詞",
        "〜が必要"
    ),
    GrammarItem(
        "Chị ấy cần tìm.",
        "彼女は探す必要があります",
        "助動詞",
        "cần + 動詞",
        "〜が必要"
    ),
    GrammarItem(
        "Tôi không cần biết.",
        "私は知る必要はありません",
        "助動詞",
        "không cần + 動詞",
        "〜は必要ない"
    ),
    GrammarItem(
        "Tôi không cần chờ tôi.",
        "私を待つ必要はありません",
        "助動詞",
        "không cần + 動詞",
        "〜は必要ない"
    ),
    GrammarItem(
        "Chúng ta không cần đi sớm.",
        "私たちは早く行く必要はありません",
        "助動詞",
        "không cần + 動詞",
        "〜は必要ない"
    ),
    GrammarItem(
        "Bạn có cần chọn không?",
        "あなたは選ぶ必要がありますか？",
        "助動詞",
        "có cần + 動詞 + không?",
        "〜する必要がありますか？"
    ),
    GrammarItem(
        "Tôi có cần mở cửa không?",
        "私はドアを開ける必要がありますか？",
        "助動詞",
        "có cần + 動詞 + không?",
        "〜する必要がありますか？"
    ),
    GrammarItem(
        "Chúng tôi có cần đến sớm không?",
        "私たちは早く到着する必要がありますか？",
        "助動詞",
        "có cần + 動詞 + không?",
        "〜する必要がありますか？"
    ),
    GrammarItem(
        "Bạn không được hút thuốc ở đây.",
        "あなたはここでたばこを吸ってはいけません",
        "助動詞",
        "không được + 動詞",
        "〜してはいけない"
    ),
    GrammarItem(
        "Anh ấy không được lái xe.",
        "彼は運転してはいけません",
        "助動詞",
        "không được + 動詞",
        "〜してはいけない"
    ),
    GrammarItem(
        "Bạn không được chụp ảnh ở đây.",
        "あなたはここで写真を撮ってはいけません",
        "助動詞",
        "không được + 動詞",
        "〜してはいけない"
    ),
    GrammarItem(
        "Bạn giúp tôi được không?",
        "手伝ってもらえますか？",
        "助動詞",
        "動詞 + được không?",
        "〜してもいいですか？"
    ),
    GrammarItem(
        "Anh ấy ngồi đây được không?",
        "彼はここに座ってもいいですか？",
        "助動詞",
        "動詞 + được không?",
        "〜してもいいですか？"
    ),
    GrammarItem(
        "Tôi mở cửa được không?",
        "ドアを開けてもいいですか？",
        "助動詞",
        "動詞 + được không?",
        "〜してもいいですか？"
    ),
    GrammarItem(
        "Tôi định mua sách.",
        "私は本を買うつもりです",
        "助動詞",
        "định + 動詞",
        "〜するつもり"
    ),
    GrammarItem(
        "Anh ấy định mua xe mới.",
        "彼は新しい車を買うつもりです",
        "助動詞",
        "định + 動詞",
        "〜するつもり"
    ),
    GrammarItem(
        "Tôi định đi bộ.",
        "私は歩くつもりです",
        "助動詞",
        "định + 動詞",
        "〜するつもり"
    ),
    GrammarItem(
        "Tôi không định nấu ăn.",
        "私は料理するつもりはありません",
        "助動詞",
        "không định + 動詞",
        "〜するつもりはありません"
    ),
    GrammarItem(
        "Anh ấy không định nói gì thêm.",
        "彼はこれ以上話すつもりはありません",
        "助動詞",
        "không định + 動詞",
        "〜するつもりはありません"
    ),
    GrammarItem(
        "Anh ấy không định gặp bạn.",
        "彼は友達に会うつもりはありません",
        "助動詞",
        "không định + 動詞",
        "〜するつもりはありません"
    ),
    GrammarItem(
        "Bạn có định gặp không?",
        "あなたは会うつもりですか？",
        "助動詞",
        "có định + 動詞 + không?",
        "〜するつもりですか？"
    ),
    GrammarItem(
        "Anh ấy có định mua không?",
        "彼は買うつもりですか？",
        "助動詞",
        "có định + 動詞 + không?",
        "〜するつもりですか？"
    ),
    GrammarItem(
        "Chị ấy có định nấu không?",
        "彼女は料理するつもりですか？",
        "助動詞",
        "có định + 動詞 + không?",
        "〜するつもりですか？"
    ),
    GrammarItem(
        "Tôi dám nói.",
        "私はあえて言います",
        "助動詞",
        "dám + 動詞",
        "あえて〜する"
    ),
    GrammarItem(
        "Bạn dám trả lời.",
        "あなたはあえて答えます",
        "助動詞",
        "dám + 動詞",
        "あえて〜する"
    ),
    GrammarItem(
        "Anh ấy dám đi bộ.",
        "彼はあえて歩きます",
        "助動詞",
        "dám + 動詞",
        "あえて〜する"
    ),
    GrammarItem(
        "Tôi không dám nói thật.",
        "私は本当のことをあえて言いません",
        "助動詞",
        "không dám + 動詞",
        "あえて〜しない／〜する勇気がない"
    ),
    GrammarItem(
        "Bạn không dám tin.",
        "あなたは信じる勇気がありません",
        "助動詞",
        "không dám + 動詞",
        "あえて〜しない／〜する勇気がない"
    ),
    GrammarItem(
        "Anh ấy không dám nhìn.",
        "彼は見る勇気がありません",
        "助動詞",
        "không dám + 動詞",
        "あえて〜しない／〜する勇気がない"
    ),
    GrammarItem(
        "Bạn có dám nói thật không?",
        "あなたは本当のことをあえて言うつもりですか？",
        "助動詞",
        "có dám + 動詞 + không?",
        "あえて〜するつもりですか？"
    ),
    GrammarItem(
        "Bạn có dám ăn món này không?",
        "あなたはこの料理をあえて食べますか？",
        "助動詞",
        "có dám + 動詞 + không?",
        "あえて〜するつもりですか？"
    ),
    GrammarItem(
        "Chị ấy có dám gặp bạn không?",
        "彼女はあなたにあえて会うつもりですか？",
        "助動詞",
        "có dám + 動詞 + không?",
        "あえて〜するつもりですか？"
    ),

    //
    // ✅ 存在・所有
    //
    GrammarItem(
        "Tôi có sách.",
        "私は本を持っています",
        "存在・所有",
        "có + 名詞",
        "〜を持っている"
    ),
    GrammarItem(
        "Anh ấy có xe.",
        "彼は車を持っています",
        "存在・所有",
        "có + 名詞",
        "〜を持っている"
    ),
    GrammarItem(
        "Chị ấy có điện thoại.",
        "彼女は携帯電話を持っています",
        "存在・所有",
        "có + 名詞",
        "〜を持っている"
    ),
    GrammarItem(
        "Tôi không có một cái dù.",
        "私は傘を持っていません",
        "存在・所有",
        "không có + 名詞",
        "〜を持っていません"
    ),
    GrammarItem(
        "Anh ấy không có máy tính.",
        "彼はパソコンを持っていません",
        "存在・所有",
        "không có + 名詞",
        "〜を持っていません"
    ),
    GrammarItem(
        "Chị ấy không có túi.",
        "彼女はバッグを持っていません",
        "存在・所有",
        "không có + 名詞",
        "〜を持っていません"
    ),
    GrammarItem(
        "Bạn có bút không?",
        "あなたはペンを持っていますか？",
        "存在・所有",
        "có + 名詞 + không?",
        "〜を持っていますか？"
    ),
    GrammarItem(
        "Anh ấy có tiền không?",
        "彼はお金を持っていますか？",
        "存在・所有",
        "có + 名詞 + không?",
        "〜を持っていますか？"
    ),
    GrammarItem(
        "Bạn có xe máy không?",
        "あなたはバイクを持っていますか？",
        "存在・所有",
        "có + 名詞 + không?",
        "〜を持っていますか？"
    ),
    GrammarItem(
        "Sách ở trên bàn.",
        "本は机の上にある",
        "存在・所有",
        "物 + ở + 場所",
        "〜にある"
    ),
    GrammarItem(
        "Điện thoại ở trong túi.",
        "携帯電話はカバンの中にある",
        "存在・所有",
        "物 + ở + 場所",
        "〜にある"
    ),
    GrammarItem(
        "Máy tính ở phòng làm việc.",
        "パソコンは仕事部屋にある",
        "存在・所有",
        "物 + ở + 場所",
        "〜にある"
    ),
    GrammarItem(
        "Nhà hàng không ở bên cạnh khách sạn.",
        "レストランはホテルの隣にありません",
        "存在・所有",
        "物 + không ở + 場所",
        "〜にありません"
    ),
    GrammarItem(
        "Thư viện không ở gần trường.",
        "図書館は学校の近くにありません",
        "存在・所有",
        "物 + không ở + 場所",
        "〜にありません"
    ),
    GrammarItem(
        "Nhà ga không ở trung tâm.",
        "駅は中心部にありません",
        "存在・所有",
        "物 + không ở + 場所",
        "〜にありません"
    ),
    GrammarItem(
        "Bánh ở trong tủ lạnh không?",
        "ケーキは冷蔵庫の中にありますか？",
        "存在・所有",
        "物 + có ở + 場所 + không?",
        "〜にありますか？"
    ),
    GrammarItem(
        "Áo ở trong phòng ngủ không?",
        "服は寝室にありますか？",
        "存在・所有",
        "物 + có ở + 場所 + không?",
        "〜にありますか？"
    ),
    GrammarItem(
        "Điện thoại ở trên bàn không?",
        "スマホは机の上にありますか？",
        "存在・所有",
        "物 + có ở + 場所 + không?",
        "〜にありますか？"
    ),
    GrammarItem(
        "Bút của bạn ở trên ghế.",
        "あなたのペンは椅子の上にあります",
        "存在・所有",
        "của 〜",
        "〜の"
    ),
    GrammarItem(
        "Máy tính của tôi rất mới.",
        "私のパソコンはとても新しいです",
        "存在・所有",
        "của 〜",
        "〜の"
    ),
    GrammarItem(
        "Túi của chị rất đẹp.",
        "あなたのかばんはとてもきれいです",
        "存在・所有",
        "của 〜",
        "〜の"
    ),

    //
    // ✅ 命令・依頼
    //
    GrammarItem(
        "Ăn đi!",
        "食べなさい",
        "命令・依頼",
        "動詞 + đi",
        "〜しなさい"
    ),
    GrammarItem(
        "Đọc đi!",
        "読みなさい",
        "命令・依頼",
        "動詞 + đi",
        "〜しなさい"
    ),
    GrammarItem(
        "Ngồi đi!",
        "座ってください",
        "命令・依頼",
        "動詞 + đi",
        "〜しなさい"
    ),
    GrammarItem(
        "Làm ơn giúp tôi.",
        "どうか私を手伝ってください",
        "命令・依頼",
        "Làm ơn + 動詞",
        "〜してください"
    ),
    GrammarItem(
        "Làm ơn nói chậm.",
        "どうかゆっくり話してください",
        "命令・依頼",
        "Làm ơn + 動詞",
        "〜してください"
    ),
    GrammarItem(
        "Làm ơn chờ tôi.",
        "どうか私を待ってください",
        "命令・依頼",
        "Làm ơn + 動詞",
        "〜してください"
    ),
    GrammarItem(
        "Vui lòng ngồi.",
        "お掛けください",
        "命令・依頼",
        "Vui lòng + 動詞",
        "〜してください（より丁寧）"
    ),
    GrammarItem(
        "Vui lòng chờ một chút.",
        "少々お待ちください",
        "命令・依頼",
        "Vui lòng + 動詞",
        "〜してください（より丁寧）"
    ),
    GrammarItem(
        "Vui lòng đọc kỹ.",
        "よくお読みください",
        "命令・依頼",
        "Vui lòng + 動詞",
        "〜してください（より丁寧）"
    ),
    GrammarItem(
        "Bạn hãy nghe kỹ.",
        "あなたはよく聞いてください",
        "命令・依頼",
        "hãy + 動詞",
        "〜してください"
    ),
    GrammarItem(
        "Bạn hãy đọc chậm lại.",
        "あなたはゆっくり読んでください",
        "命令・依頼",
        "hãy + 動詞",
        "〜してください"
    ),
    GrammarItem(
        "Anh ấy hãy đứng dậy.",
        "彼は立ってください",
        "命令・依頼",
        "hãy + 動詞",
        "〜してください"
    ),
    GrammarItem(
        "Anh ấy vui lòng mở cửa giúp tôi.",
        "どうぞドアを開けてください",
        "命令・依頼",
        "vui lòng + 動詞",
        "どうぞ〜してください"
    ),
    GrammarItem(
        "Bạn vui lòng chờ một chút.",
        "どうぞ少しお待ちください",
        "命令・依頼",
        "vui lòng + 動詞",
        "どうぞ〜してください"
    ),
    GrammarItem(
        "Anh ấy vui lòng đưa tôi quyển sách.",
        "どうぞ私にその本を渡してください",
        "命令・依頼",
        "vui lòng + 動詞",
        "どうぞ〜してください"
    ),
    GrammarItem(
        "Tôi nghĩ bạn nên học mỗi ngày.",
        "あなたは毎日勉強したらいいと思います",
        "命令・依頼",
        "nghĩ + 動詞",
        "〜したらいいと思う"
    ),
    GrammarItem(
        "Tôi nghĩ bạn nên ngủ sớm hơn.",
        "もっと早く寝たほうがいいと思います",
        "命令・依頼",
        "nghĩ + 動詞",
        "〜したらいいと思う"
    ),
    GrammarItem(
        "Tôi nghĩ bạn nên mua cái này.",
        "これを買ったほうがいいと思います",
        "命令・依頼",
        "nghĩ + 動詞",
        "〜したらいいと思う"
    ),
    GrammarItem(
        "Tôi khuyên bạn nên đọc sách này.",
        "この本を読むことをおすすめします",
        "命令・依頼",
        "khuyên + 動詞",
        "〜することをおすすめする"
    ),
    GrammarItem(
        "Tôi khuyên bạn nên chạy bộ mỗi sáng.",
        "毎朝ランニングすることをおすすめします",
        "命令・依頼",
        "khuyên + 動詞",
        "〜することをおすすめする"
    ),
    GrammarItem(
        "Tôi khuyên anh ấy nên đọc sách nhiều hơn.",
        "彼がもっと本を読むことをおすすめします",
        "命令・依頼",
        "khuyên + 動詞",
        "〜することをおすすめする"
    ),
    GrammarItem(
        "Bạn viết thử câu này thì sao?",
        "この文を書いてみるのはどうですか？",
        "命令・依頼",
        "〜 thì sao?",
        "〜はいかがですか？"
    ),
    GrammarItem(
        "Anh ấy đi bộ một chút thì sao?",
        "少し歩いてみるのはどうですか？",
        "命令・依頼",
        "〜 thì sao?",
        "〜はいかがですか？"
    ),
    GrammarItem(
        "Chị ấy thử đọc sách tiếng Việt thì sao?",
        "ベトナム語の本を読んでみるのはどうですか？",
        "命令・依頼",
        "〜 thì sao?",
        "〜はいかがですか？"
    ),

    //
    // ✅ 禁止
    //
    GrammarItem(
        "Đừng nói quá to.",
        "大きな声で話さないでください",
        "禁止",
        "Đừng + 動詞",
        "〜しないでください"
    ),
    GrammarItem(
        "Đừng mở cửa.",
        "ドアを開けないでください",
        "禁止",
        "Đừng + 動詞",
        "〜しないでください"
    ),
    GrammarItem(
        "Đừng chạy trong phòng.",
        "部屋の中で走らないでください",
        "禁止",
        "Đừng + 動詞",
        "〜しないでください"
    ),
    GrammarItem(
        "Không được hút thuốc ở đây.",
        "ここでたばこを吸ってはいけません",
        "禁止",
        "Không được + 動詞",
        "〜してはいけない"
    ),
    GrammarItem(
        "Không được ăn trong lớp học.",
        "教室で食べてはいけません",
        "禁止",
        "Không được + 動詞",
        "〜してはいけない"
    ),
    GrammarItem(
        "Không được mở cửa.",
        "ドアを開けてはいけません",
        "禁止",
        "Không được + 動詞",
        "〜してはいけない"
    ),
    GrammarItem(
        "Đừng quên nhé.",
        "忘れないでね",
        "禁止",
        "Đừng 〜 nhé",
        "〜しないでね"
    ),
    GrammarItem(
        "Đừng đi nhé.",
        "行かないでね",
        "禁止",
        "Đừng 〜 nhé",
        "〜しないでね"
    ),
    GrammarItem(
        "Đừng chờ tôi nhé.",
        "私を待たないでね",
        "禁止",
        "Đừng 〜 nhé",
        "〜しないでね"
    ),
    GrammarItem(
        "Cấm hút thuốc ở đây.",
        "ここでの喫煙は禁止です",
        "禁止",
        "cấm + 動詞",
        "〜を禁止する"
    ),
    GrammarItem(
        "Cấm mang đồ ăn vào phòng này.",
        "この部屋に飲食物を持ち込むのは禁止です",
        "禁止",
        "cấm + 動詞",
        "〜を禁止する"
    ),
    GrammarItem(
        "Cấm chạy trong hành lang.",
        "廊下を走るのは禁止です",
        "禁止",
        "cấm + 動詞",
        "〜を禁止する"
    ),
    GrammarItem(
        "Chớ mở cửa khi trời đang gió mạnh.",
        "強風のときにドアを開けるな",
        "禁止",
        "chớ + 動詞",
        "〜するな"
    ),
    GrammarItem(
        "Chớ đến muộn nữa.",
        "もう遅刻するな",
        "禁止",
        "chớ + 動詞",
        "〜するな"
    ),
    GrammarItem(
        "Chớ gọi tôi lúc khuya.",
        "深夜に私へ電話するな",
        "禁止",
        "chớ + 動詞",
        "〜するな"
    ),

    //
    // ✅ 比較
    //
    GrammarItem(
        "Lan đẹp bằng Mai.",
        "ランはマイと同じくらいきれいです",
        "比較",
        "形容詞 + A + bằng + B",
        "AはBと同じくらい〜です"
    ),
    GrammarItem(
        "Tôi cao bằng anh ấy.",
        "私は彼と同じくらい背が高いです",
        "比較",
        "形容詞 + A + bằng + B",
        "AはBと同じくらい〜です"
    ),
    GrammarItem(
        "Xe này nhanh bằng xe kia.",
        "この車はあの車と同じくらい速いです",
        "比較",
        "形容詞 + A + bằng + B",
        "AはBと同じくらい〜です"
    ),
    GrammarItem(
        "Lan không đẹp bằng Mai.",
        "ランさんはマイさんほどきれいではありません",
        "比較",
        "A + không + 形容詞 + bằng + B",
        "AはBほど〜ではない"
    ),
    GrammarItem(
        "Xe đạp không nhanh bằng xe máy.",
        "自転車はバイクほど速くありません",
        "比較",
        "A + không + 形容詞 + bằng + B",
        "AはBほど〜ではない"
    ),
    GrammarItem(
        "Tôi không bận bằng bạn.",
        "私はあなたほど忙しくない",
        "比較",
        "A + không + 形容詞 + bằng + B",
        "AはBほど〜ではない"
    ),
    GrammarItem(
        "Lan đẹp bằng Mai không?",
        "ランはマイと同じくらいきれいですか？",
        "比較",
        "形容詞 + A + bằng + B + không?",
        "AはBと同じくらい〜ですか？"
    ),
    GrammarItem(
        "Nhà anh lớn bằng nhà tôi không?",
        "あなたの家は私の家と同じくらい大きいですか？",
        "比較",
        "形容詞 + A + bằng + B + không?",
        "AはBと同じくらい〜ですか？"
    ),
    GrammarItem(
        "Xe này nhanh bằng xe kia không?",
        "この車はあの車と同じくらい速いですか？",
        "比較",
        "形容詞 + A + bằng + B + không?",
        "AはBと同じくらい〜ですか？"
    ),
    GrammarItem(
        "Lan đẹp hơn Mai.",
        "ランはマイよりきれいです",
        "比較",
        "A + 形容詞 + hơn + B",
        "AはBより〜です"
    ),
    GrammarItem(
        "Tôi cao hơn bạn.",
        "私はあなたより背が高いです",
        "比較",
        "A + 形容詞 + hơn + B",
        "AはBより〜です"
    ),
    GrammarItem(
        "Việc này khó hơn việc kia.",
        "この仕事はあれより難しいです",
        "比較",
        "A + 形容詞 + hơn + B",
        "AはBより〜です"
    ),
    GrammarItem(
        "Lan đẹp nhất lớp.",
        "ランはクラスで一番きれいです",
        "比較",
        "形容詞 + nhất",
        "一番〜"
    ),
    GrammarItem(
        "Đây là món ngon nhất.",
        "これは一番おいしい料理です",
        "比較",
        "形容詞 + nhất",
        "一番〜"
    ),
    GrammarItem(
        "Anh ấy cao nhất công ty.",
        "彼は会社で一番背が高いです",
        "比較",
        "形容詞 + nhất",
        "一番〜"
    ),
    GrammarItem(
        "Việc này khó hơn nữa.",
        "この仕事はさらに難しいです",
        "比較",
        "形容詞 + hơn nữa",
        "もっと～、さらに～"
    ),
    GrammarItem(
        "Tôi muốn học nhiều hơn nữa.",
        "私はもっとたくさん勉強したいです",
        "比較",
        "形容詞 + hơn nữa",
        "もっと～、さらに～"
    ),
    GrammarItem(
        "Món này rẻ hơn nữa.",
        "この料理はさらに安いです",
        "比較",
        "形容詞 + hơn nữa",
        "もっと～、さらに～"
    ),
    GrammarItem(
        "Hôm nay còn nóng hơn hôm qua.",
        "今日は昨日よりさらに暑いです",
        "比較",
        "còn + 形容詞 + hơn",
        "さらに〜です"
    ),
    GrammarItem(
        "Bộ phim này còn buồn hơn.",
        "この映画はさらに悲しいです",
        "比較",
        "còn + 形容詞 + hơn",
        "さらに〜です"
    ),
    GrammarItem(
        "Phòng này còn bẩn hơn.",
        "この部屋はさらに汚いです",
        "比較",
        "còn + 形容詞 + hơn",
        "さらに〜です"
    ),
    GrammarItem(
        "Càng học càng giỏi.",
        "勉強すればするほど上達します",
        "比較",
        "càng 〜 càng …",
        "〜すればするほど…です"
    ),
    GrammarItem(
        "Càng đọc càng hiểu.",
        "読めば読むほど理解できます",
        "比較",
        "càng 〜 càng …",
        "〜すればするほど…です"
    ),
    GrammarItem(
        "Càng làm càng mệt.",
        "やればやるほど疲れます",
        "比較",
        "càng 〜 càng …",
        "〜すればするほど…です"
    ),

    //
    // ✅ 受動態・使役
    //
    GrammarItem(
        "Tôi cho bạn xem ảnh này.",
        "私はあなたにこの写真を見せます",
        "受動態・使役",
        "cho + 人 + 動詞",
        "〜させる／〜してもらう"
    ),
    GrammarItem(
        "Chị ấy cho tôi mượn bút.",
        "彼女は私にペンを貸してくれます",
        "受動態・使役",
        "cho + 人 + 動詞",
        "〜させる／〜してもらう"
    ),
    GrammarItem(
        "Tôi cho anh ấy vào phòng.",
        "私は彼を部屋に入らせます",
        "受動態・使役",
        "cho + 人 + 動詞",
        "〜させる／〜してもらう"
    ),
    GrammarItem(
        "Cho tôi nước.",
        "私に水をください",
        "受動態・使役",
        "cho tôi + 名詞",
        "私に〜をください"
    ),
    GrammarItem(
        "Cho tôi sách.",
        "私に本をください",
        "受動態・使役",
        "cho tôi + 名詞",
        "私に〜をください"
    ),
    GrammarItem(
        "Cho tôi cà phê.",
        "私にコーヒーをください",
        "受動態・使役",
        "cho tôi + 名詞",
        "私に〜をください"
    ),
    GrammarItem(
        "Tôi để bạn xem.",
        "私はあなたに見させる",
        "受動態・使役",
        "để + 人 + 動詞",
        "〜させておく／〜できるようにする"
    ),
    GrammarItem(
        "Anh ấy để chị ấy nghỉ.",
        "彼は彼女を休ませる",
        "受動態・使役",
        "để + 人 + 動詞",
        "〜させておく／〜できるようにする"
    ),
    GrammarItem(
        "Bạn để tôi nói.",
        "あなたは私に話させる",
        "受動態・使役",
        "để + 人 + 動詞",
        "〜させておく／〜できるようにする"
    ),
    GrammarItem(
        "Tôi bị mất tiền.",
        "私はお金を失ってしまいました",
        "受動態・使役",
        "bị + 動詞",
        "〜されてしまう・被害を受ける"
    ),
    GrammarItem(
        "Anh ấy bị quên.",
        "彼は忘れられてしまいました",
        "受動態・使役",
        "bị + 動詞",
        "〜されてしまう・被害を受ける"
    ),
    GrammarItem(
        "Xe máy bị hỏng.",
        "バイクが壊れてしまいました",
        "受動態・使役",
        "bị + 動詞",
        "〜されてしまう・被害を受ける"
    ),
    GrammarItem(
        "Tôi không bị lạc đường.",
        "私は道に迷いませんでした",
        "受動態・使役",
        "không bị + 動詞",
        "〜されなかった"
    ),
    GrammarItem(
        "Tôi không bị cảm.",
        "私は風邪をひきませんでした",
        "受動態・使役",
        "không bị + 動詞",
        "〜されなかった"
    ),
    GrammarItem(
        "Bạn không bị trễ.",
        "あなたは遅れませんでした",
        "受動態・使役",
        "không bị + 動詞",
        "〜されなかった"
    ),
    GrammarItem(
        "Anh ấy bị cảm không?",
        "彼は風邪をひきましたか？",
        "受動態・使役",
        "bị + 動詞 + không?",
        "〜されましたか？"
    ),
    GrammarItem(
        "Chị ấy bị ngã không?",
        "彼女は転びましたか？",
        "受動態・使役",
        "bị + 動詞 + không?",
        "〜されましたか？"
    ),
    GrammarItem(
        "Bạn bị mất điện thoại không?",
        "あなたは携帯電話をなくしましたか？",
        "受動態・使役",
        "bị + 動詞 + không?",
        "〜されましたか？"
    ),
    GrammarItem(
        "Tôi được trả tiền",
        "私はお金を払ってもらいます",
        "受動態・使役",
        "được + 動詞",
        "〜してもらう・利益を受ける"
    ),
    GrammarItem(
        "Tôi được mời.",
        "私は招待されます",
        "受動態・使役",
        "được + 動詞",
        "〜してもらう・利益を受ける"
    ),
    GrammarItem(
        "Chúng tôi được giúp đỡ.",
        "私たちは助けてもらいます",
        "受動態・使役",
        "được + 動詞",
        "〜してもらう・利益を受ける"
    ),
    GrammarItem(
        "Tôi không được giúp.",
        "私は助けてもらえませんでした",
        "受動態・使役",
        "không được + 動詞",
        "〜してもらえなかった"
    ),
    GrammarItem(
        "Tôi không được mời.",
        "私は招待してもらえませんでした",
        "受動態・使役",
        "không được + 動詞",
        "〜してもらえなかった"
    ),
    GrammarItem(
        "Bạn không được tin.",
        "あなたは信じてもらえませんでした",
        "受動態・使役",
        "không được + 動詞",
        "〜してもらえなかった"
    ),
    GrammarItem(
        "Bạn được giúp không?",
        "あなたは助けてもらえましたか？",
        "受動態・使役",
        "được + 動詞 + không?",
        "〜してもらえましたか？"
    ),
    GrammarItem(
        "Bạn được nghỉ không?",
        "あなたは休みをもらえましたか？",
        "受動態・使役",
        "được + 動詞 + không?",
        "〜してもらえましたか？"
    ),
    GrammarItem(
        "Chị ấy được gặp bạn không?",
        "彼女はあなたに会えましたか？",
        "受動態・使役",
        "được + 動詞 + không?",
        "〜してもらえましたか？"
    ),

    //
    // ✅ 接続詞
    //
    GrammarItem(
        "Nếu tôi rảnh thì tôi gặp bạn.",
        "時間があれば私はあなたに会います",
        "接続詞",
        "Nếu A thì B",
        "もしAならB"
    ),
    GrammarItem(
        "Nếu có vấn đề thì gọi tôi.",
        "問題があれば私に電話してください",
        "接続詞",
        "Nếu A thì B",
        "もしAならB"
    ),
    GrammarItem(
        "Nếu bạn cần thì tôi giúp.",
        "必要なら私は手伝います",
        "接続詞",
        "Nếu A thì B",
        "もしAならB"
    ),
    GrammarItem(
        "Vì bận nên tôi không gặp bạn.",
        "忙しいのであなたに会えません",
        "接続詞",
        "Vì A nên B",
        "AだからB"
    ),
    GrammarItem(
        "Vì mệt nên tôi nghỉ.",
        "疲れているので休みます",
        "接続詞",
        "Vì A nên B",
        "AだからB"
    ),
    GrammarItem(
        "Vì trời mưa nên tôi ở nhà.",
        "雨なので私は家にいる",
        "接続詞",
        "Vì A nên B",
        "AだからB"
    ),
    GrammarItem(
        "Tôi ăn cơm và uống nước.",
        "私はご飯を食べて水を飲みます",
        "接続詞",
        "A và B",
        "AそしてB"
    ),
    GrammarItem(
        "Bạn đọc sách và viết thư.",
        "あなたは本を読んで手紙を書きます",
        "接続詞",
        "A và B",
        "AそしてB"
    ),
    GrammarItem(
        "Tôi học và nghe nhạc.",
        "私は勉強して音楽を聴きます",
        "接続詞",
        "A và B",
        "AそしてB"
    ),
    GrammarItem(
        "Tôi muốn đi nhưng tôi bận.",
        "私は行きたいけど忙しいです",
        "接続詞",
        "A nhưng B",
        "AだけどB"
    ),
    GrammarItem(
        "Món này ngon, nhưng hơi đắt.",
        "この料理はおいしいけど少し高いです",
        "接続詞",
        "A nhưng B",
        "AだけどB"
    ),
    GrammarItem(
        "Anh ấy có xe nhưng anh ấy không lái.",
        "彼は車を持っているけど運転しません",
        "接続詞",
        "A nhưng B",
        "AだけどB"
    ),
    GrammarItem(
        "Anh ấy uống trà hay cà phê?",
        "彼はお茶とコーヒー、どちらを飲みますか？",
        "接続詞",
        "A hay B?",
        "AそれともB？"
    ),
    GrammarItem(
        "Tôi đi xe buýt hay đi bộ?",
        "バスで、それとも歩いて行きますか？",
        "接続詞",
        "A hay B?",
        "AそれともB？"
    ),
    GrammarItem(
        "Bạn ăn ở nhà hay nhà hàng?",
        "家で、それともレストランで食べますか？",
        "接続詞",
        "A hay B?",
        "AそれともB？"
    ),
    GrammarItem(
        "Bạn có thể uống trà hoặc cà phê.",
        "お茶またはコーヒーを飲むことができます",
        "接続詞",
        "A hoặc B",
        "AまたはB"
    ),
    GrammarItem(
        "Bạn có thể đi xe buýt hoặc taxi.",
        "バスまたはタクシーで行くことができます",
        "接続詞",
        "A hoặc B",
        "AまたはB"
    ),
    GrammarItem(
        "Anh ấy làm việc ở công ty hoặc ở nhà.",
        "彼は会社または家で働きます",
        "接続詞",
        "A hoặc B",
        "AまたはB"
    ),
    GrammarItem(
        "Khi rảnh, tôi đọc sách.",
        "暇なとき、私は本を読みます",
        "接続詞",
        "Khi 〜, …",
        "〜するとき、…します"
    ),
    GrammarItem(
        "Khi làm việc, tôi nghe nhạc.",
        "仕事するとき、私は音楽を聴きます",
        "接続詞",
        "Khi 〜, …",
        "〜するとき、…します"
    ),
    GrammarItem(
        "Khi ăn, bạn uống nước.",
        "食べるとき、あなたは水を飲みます",
        "接続詞",
        "Khi 〜, …",
        "〜するとき、…します"
    ),
    GrammarItem(
        "Tôi đi với bạn.",
        "私はあなたと一緒に行きます",
        "接続詞",
        "với 〜",
        "〜と一緒に"
    ),
    GrammarItem(
        "Bạn học với tôi.",
        "あなたは私と一緒に勉強します",
        "接続詞",
        "với 〜",
        "〜と一緒に"
    ),
    GrammarItem(
        "Anh ấy ăn với chị ấy.",
        "彼は彼女と一緒に食事をします",
        "接続詞",
        "với 〜",
        "〜と一緒に"
    ),
    GrammarItem(
        "Tôi đưa sách cho bạn.",
        "私はあなたに本を渡します",
        "接続詞",
        "cho 〜",
        "〜に"
    ),
    GrammarItem(
        "Chị ấy mua cà phê cho anh ấy.",
        "彼女は彼にコーヒーを買います",
        "接続詞",
        "cho 〜",
        "〜に"
    ),
    GrammarItem(
        "Tôi trả tiền cho bạn.",
        "私はあなたにお金を払います",
        "接続詞",
        "cho 〜",
        "〜に"
    ),
    GrammarItem(
        "Chị ấy thử đọc sách mới.",
        "彼女は新しい本を読んでみる",
        "接続詞",
        "thử + 動詞",
        "〜してみる"
    ),
    GrammarItem(
        "Tôi thử ăn món này.",
        "私はこの料理を食べてみる",
        "接続詞",
        "thử + 動詞",
        "〜してみる"
    ),
    GrammarItem(
        "Tôi thử đi xe máy.",
        "私はバイクに乗ってみる",
        "接続詞",
        "thử + 動詞",
        "〜してみる"
    ),
    GrammarItem(
        "Tôi đi bằng taxi.",
        "私はタクシーで行きます",
        "接続詞",
        "動詞 + bằng + 名詞",
        "〜で…する"
    ),
    GrammarItem(
        "Anh ấy viết bằng bút chì.",
        "彼は鉛筆で書きます",
        "接続詞",
        "動詞 + bằng + 名詞",
        "〜で…する"
    ),
    GrammarItem(
        "Chị ấy trả tiền bằng thẻ.",
        "彼女はカードで支払います",
        "接続詞",
        "動詞 + bằng + 名詞",
        "〜で…する"
    ),
    GrammarItem(
        "Tôi học để làm việc.",
        "私は仕事をするために勉強します",
        "接続詞",
        "để + 動詞",
        "〜するために"
    ),
    GrammarItem(
        "Chị ấy ăn để khỏe.",
        "彼女は健康のために食べます",
        "接続詞",
        "để + 動詞",
        "〜するために"
    ),
    GrammarItem(
        "Bạn đi để gặp bạn.",
        "あなたは友達に会うために行きます",
        "接続詞",
        "để + 動詞",
        "〜するために"
    ),
    GrammarItem(
        "Tôi muốn đi Đà Nẵng nữa.",
        "私はまたダナンへ行きたい",
        "接続詞",
        "〜 nữa",
        "また〜する"
    ),
    GrammarItem(
        "Chị ấy đọc sách nữa.",
        "彼女はまた本を読みます",
        "接続詞",
        "〜 nữa",
        "また〜する"
    ),
    GrammarItem(
        "Tôi ăn nữa.",
        "私はまた食べます",
        "接続詞",
        "〜 nữa",
        "また〜する"
    ),
    GrammarItem(
        "Tôi nói vậy thôi.",
        "私はそれだけ言う",
        "接続詞",
        "〜 thôi",
        "〜だけ／やめる"
    ),
    GrammarItem(
        "Bạn xem chút thôi.",
        "あなたは少し見るだけ",
        "接続詞",
        "〜 thôi",
        "〜だけ／やめる"
    ),
    GrammarItem(
        "Chị ấy làm đến đây thôi.",
        "彼女はここまででやめる",
        "接続詞",
        "〜 thôi",
        "〜だけ／やめる"
    ),
    GrammarItem(
        "Tôi đi làm từ 8 giờ đến 5 giờ.",
        "私は8時から5時まで仕事に行きます",
        "接続詞",
        "từ ～ đến…",
        "〜から…まで"
    ),
    GrammarItem(
        "Anh ấy chạy từ nhà đến công ty.",
        "彼は家から会社まで走ります",
        "接続詞",
        "từ ～ đến…",
        "〜から…まで"
    ),
    GrammarItem(
        "Chị ấy nấu từ sáng đến trưa.",
        "彼女は朝から昼まで料理します",
        "接続詞",
        "từ ～ đến…",
        "〜から…まで"
    ),

    //
    // ✅ 強調
    //
    GrammarItem(
        "Đây chính là nhà của tôi.",
        "これがまさに私の家です",
        "強調",
        "chính là 〜",
        "まさに〜です"
    ),
    GrammarItem(
        "Bạn chính là bạn của tôi.",
        "あなたこそが私の友達です",
        "強調",
        "chính là 〜",
        "まさに〜です"
    ),
    GrammarItem(
        "Chị ấy chính là người tôi tìm.",
        "彼女がまさに私が探している人です",
        "強調",
        "chính là 〜",
        "まさに〜です"
    ),
    GrammarItem(
        "Tôi chỉ uống nước.",
        "私は水だけ飲みます",
        "強調",
        "chỉ + 動詞",
        "だけ〜します"
    ),
    GrammarItem(
        "Tôi chỉ học tiếng Việt.",
        "私はベトナム語だけ勉強します",
        "強調",
        "chỉ + 動詞",
        "だけ〜します"
    ),
    GrammarItem(
        "Hôm nay tôi chỉ đọc sách.",
        "今日は本を読むだけです",
        "強調",
        "chỉ + 動詞",
        "だけ〜します"
    ),
    GrammarItem(
        "Tôi cũng mua sách.",
        "私も本を買います",
        "強調",
        "主語 + cũng",
        "〜も"
    ),
    GrammarItem(
        "Anh ấy cũng biết tiếng Nhật.",
        "彼も日本語を知っています",
        "強調",
        "主語 + cũng",
        "〜も"
    ),
    GrammarItem(
        "Tôi cũng muốn đi.",
        "私も行きたいです",
        "強調",
        "主語 + cũng",
        "〜も"
    ),

    //
    // ✅ 文末詞
    //
    GrammarItem(
        "Anh ấy đi à?",
        "行くの？",
        "文末詞",
        "〜 à?",
        "〜なの？（確認・驚き）"
    ),
    GrammarItem(
        "Bạn học tiếng Việt à?",
        "ベトナム語を勉強しているの？",
        "文末詞",
        "〜 à?",
        "〜なの？（確認・驚き）"
    ),
    GrammarItem(
        "Chị ấy mua xe mới à?",
        "新しい車を買ったの？",
        "文末詞",
        "〜 à?",
        "〜なの？（確認・驚き）"
    ),
    GrammarItem(
        "Bạn chờ một chút ạ.",
        "少しお待ちください",
        "文末詞",
        "〜 ạ",
        "敬語・丁寧"
    ),
    GrammarItem(
        "Tôi hiểu rồi ạ.",
        "私は理解しました",
        "文末詞",
        "〜 ạ",
        "敬語・丁寧"
    ),
    GrammarItem(
        "Anh ấy không biết ạ.",
        "彼は知りません",
        "文末詞",
        "〜 ạ",
        "敬語・丁寧"
    ),
    GrammarItem(
        "Bạn nghe tôi nhé.",
        "私の話を聞いてね",
        "文末詞",
        "〜 nhé",
        "〜ね（お願い・確認）"
    ),
    GrammarItem(
        "Chị ấy nói lại nhé.",
        "もう一度言ってね",
        "文末詞",
        "〜 nhé",
        "〜ね（お願い・確認）"
    ),
    GrammarItem(
        "Tôi gọi bạn nhé.",
        "あなたに電話するね",
        "文末詞",
        "〜 nhé",
        "〜ね（お願い・確認）"
    ),
    GrammarItem(
        "Món này ngon đấy.",
        "この料理はおいしいですよ",
        "文末詞",
        "〜 đấy",
        "〜ですよ（強調）"
    ),
    GrammarItem(
        "Anh ấy biết tiếng Nhật đấy.",
        "彼は日本語を知っていますよ",
        "文末詞",
        "〜 đấy",
        "〜ですよ（強調）"
    ),
    GrammarItem(
        "Trời lạnh đấy.",
        "寒いですよ",
        "文末詞",
        "〜 đấy",
        "〜ですよ（強調）"
    ),
    GrammarItem(
        "Tôi biết cơ.",
        "知っているんだよ",
        "文末詞",
        "〜 cơ",
        "〜なんだよ（気持ち・反論）"
    ),
    GrammarItem(
        "Tôi không quên cơ.",
        "忘れてないんだよ",
        "文末詞",
        "〜 cơ",
        "〜なんだよ（気持ち・反論）"
    ),
    GrammarItem(
        "Món này ngon cơ.",
        "この料理はおいしいんだよ",
        "文末詞",
        "〜 cơ",
        "〜なんだよ（気持ち・反論）"
    ),
    GrammarItem(
        "Tôi đã làm rồi chứ.",
        "もちろんもうやったよ",
        "文末詞",
        "〜 chứ",
        "もちろん〜だよ（確認・当然）"
    ),
    GrammarItem(
        "Bạn sẽ đi chứ.",
        "もちろん行くよね",
        "文末詞",
        "〜 chứ",
        "もちろん〜だよ（確認・当然）"
    ),
    GrammarItem(
        "Anh ấy đã biết chứ.",
        "もちろん彼はもう知ってるよ",
        "文末詞",
        "〜 chứ",
        "もちろん〜だよ（確認・当然）"
    ),
    GrammarItem(
        "Đi hả?",
        "行くの？",
        "文末詞",
        "〜 hả?",
        "〜なの？（くだけた疑問）"
    ),
    GrammarItem(
        "Ăn rồi hả?",
        "もう食べたの？",
        "文末詞",
        "〜 hả?",
        "〜なの？（くだけた疑問）"
    ),
    GrammarItem(
        "Mệt hả?",
        "疲れたの？",
        "文末詞",
        "〜 hả?",
        "〜なの？（くだけた疑問）"
    ),
    GrammarItem(
        "Anh ấy không đi sao?",
        "どうして行かないの？",
        "文末詞",
        "〜 sao?",
        "なんで〜なの？（理由）"
    ),
    GrammarItem(
        "Bạn buồn sao?",
        "なんで悲しいの？",
        "文末詞",
        "〜 sao?",
        "なんで〜なの？（理由）"
    ),
    GrammarItem(
        "Chị ấy không ăn sao?",
        "どうして食べないの？",
        "文末詞",
        "〜 sao?",
        "なんで〜なの？（理由）"
    ),
    GrammarItem(
        "Sao muộn vậy?",
        "どうしてこんなに遅いの？",
        "文末詞",
        "〜 vậy?",
        "そんな状態なの？（驚き・理由）"
    ),
    GrammarItem(
        "Mệt vậy?",
        "そんなに疲れているの？",
        "文末詞",
        "〜 vậy?",
        "そんな状態なの？（驚き・理由）"
    ),
    GrammarItem(
        "Đi sớm vậy?",
        "そんなに早く行くの？",
        "文末詞",
        "〜 vậy?",
        "そんな状態なの？（驚き・理由）"
    ),
    GrammarItem(
        "Tôi bận mà.",
        "忙しいんだよ",
        "文末詞",
        "〜 mà",
        "〜なのに（理由・不満）"
    ),
    GrammarItem(
        "Tôi không biết mà.",
        "知らなかったんだよ",
        "文末詞",
        "〜 mà",
        "〜なのに（理由・不満）"
    ),
    GrammarItem(
        "Trời mưa mà.",
        "雨なんだよ",
        "文末詞",
        "〜 mà",
        "〜なのに（理由・不満）"
    ),
    GrammarItem(
        "Đẹp nhỉ?",
        "きれいだね？",
        "文末詞",
        "〜 nhỉ?",
        "〜だよね？（同意を求める）"
    ),
    GrammarItem(
        "Khó nhỉ?",
        "難しいよね？",
        "文末詞",
        "〜 nhỉ?",
        "〜だよね？（同意を求める）"
    ),
    GrammarItem(
        "Ngon nhỉ?",
        "おいしいよね？",
        "文末詞",
        "〜 nhỉ?",
        "〜だよね？（同意を求める）"
    ),
    GrammarItem(
        "Giúp tôi với!",
        "手伝ってよ！",
        "文末詞",
        "〜 với",
        "〜してよ（親しいお願い）"
    ),
    GrammarItem(
        "Chờ tôi với!",
        "待ってよ！",
        "文末詞",
        "〜 với",
        "〜してよ（親しいお願い）"
    ),
    GrammarItem(
        "Nói lại với!",
        "もう一度言ってよ！",
        "文末詞",
        "〜 với",
        "〜してよ（親しいお願い）"
    ),
    GrammarItem(
        "Tôi không biết đâu.",
        "私は知らないよ",
        "文末詞",
        "〜 đâu",
        "〜ではない（否定の強調）"
    ),
    GrammarItem(
        "Chị ấy không giận đâu.",
        "彼女は怒ってないよ",
        "文末詞",
        "〜 đâu",
        "〜ではない（否定の強調）"
    ),
    GrammarItem(
        "Anh ấy không bận đâu.",
        "彼は忙しくないよ",
        "文末詞",
        "〜 đâu",
        "〜ではない（否定の強調）"
    )
)
