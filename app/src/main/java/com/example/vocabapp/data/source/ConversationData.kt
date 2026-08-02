package com.example.vocabapp.data.source

data class ConversationItem(
    val vietnamese: String,
    val japanese: String,
    val theme: String,
    val part: String,
    val pattern: String,
    val memo: String
)

val conversationList = listOf(

    //
    // ✅ Part1 基本文型
    //
    ConversationItem(
        "Tôi muốn đi du lịch",
        "私は旅行に行きたいです",
        "一般",
        "基本文型",
        "muốn + 動詞",
        "〜したい"
    ),
    ConversationItem(
        "Tôi muốn nghỉ hôm nay",
        "私は今日は休みたいです",
        "一般",
        "基本文型",
        "muốn + 動詞",
        "〜したい"
    ),
    ConversationItem(
        "Tôi muốn ăn món này",
        "私はこの料理を食べたいです",
        "一般",
        "基本文型",
        "muốn + 動詞",
        "〜したい"
    ),
    ConversationItem(
        "Tôi phải làm việc hôm nay",
        "私は今日働かなければなりません",
        "一般",
        "基本文型",
        "phải + 動詞",
        "〜しなければならない"
    ),
    ConversationItem(
        "Tôi phải đi ngay",
        "私はすぐに行かなければなりません",
        "一般",
        "基本文型",
        "phải + 動詞",
        "〜しなければならない"
    ),
    ConversationItem(
        "Tôi phải trả lời ngay",
        "私はすぐに返事しなければなりません",
        "一般",
        "基本文型",
        "phải + 動詞",
        "〜しなければならない"
    ),
    ConversationItem(
        "Tôi nên nghỉ ngơi",
        "私は休むべきです",
        "一般",
        "基本文型",
        "nên + 動詞",
        "〜すべき"
    ),
    ConversationItem(
        "Tôi nên đi sớm",
        "私は早く行くべきです",
        "一般",
        "基本文型",
        "nên + 動詞",
        "〜すべき"
    ),
    ConversationItem(
        "Tôi nên hỏi lại",
        "私は聞き直すべきです",
        "一般",
        "基本文型",
        "nên + 動詞",
        "〜すべき"
    ),
    ConversationItem(
        "Tôi cần thời gian",
        "私は時間が必要です",
        "一般",
        "基本文型",
        "cần + 名詞",
        "〜が必要"
    ),
    ConversationItem(
        "Tôi cần giúp đỡ",
        "私は助けが必要です",
        "一般",
        "基本文型",
        "cần + 名詞",
        "〜が必要"
    ),
    ConversationItem(
        "Tôi cần thêm thông tin",
        "もう少し情報が必要です",
        "一般",
        "基本文型",
        "cần + 名詞",
        "〜が必要"
    ),
    ConversationItem(
        "Tôi cần nghỉ một chút",
        "私は少し休む必要があります",
        "一般",
        "基本文型",
        "cần + 動詞",
        "〜する必要がある"
    ),
    ConversationItem(
        "Tôi cần kiểm tra thêm",
        "もう一度確認する必要があります",
        "一般",
        "基本文型",
        "cần + 動詞",
        "〜する必要がある"
    ),
    ConversationItem(
        "Tôi cần bạn giải thích thêm",
        "もう少し説明が必要です",
        "一般",
        "基本文型",
        "cần + 動詞",
        "〜する必要がある"
    ),

    //
    // ✅ Part2 依頼
    //
    ConversationItem(
        "Bạn giúp tôi được không?",
        "手伝ってもらえますか？",
        "一般",
        "依頼",
        "được không",
        "依頼"
    ),
    ConversationItem(
        "Bạn nói lại được không?",
        "もう一度言ってもらえますか？",
        "一般",
        "依頼",
        "được không",
        "依頼"
    ),
    ConversationItem(
        "Bạn chờ tôi một chút được không?",
        "少し待ってもらえますか？",
        "一般",
        "依頼",
        "được không",
        "依頼"
    ),
    ConversationItem(
        "Bạn có thể giúp tôi không?",
        "手伝ってもらえますか？",
        "一般",
        "依頼",
        "có thể + 動詞",
        "丁寧依頼"
    ),
    ConversationItem(
        "Bạn có thể nói lại không?",
        "もう一度言ってもらえますか？",
        "一般",
        "依頼",
        "có thể + 動詞",
        "丁寧依頼"
    ),
    ConversationItem(
        "Bạn có thể kiểm tra giúp tôi không?",
        "確認してもらえますか？",
        "一般",
        "依頼",
        "có thể + 動詞",
        "丁寧依頼"
    ),
    ConversationItem(
        "Làm ơn giúp tôi",
        "手伝ってください",
        "一般",
        "依頼",
        "làm ơn",
        "依頼"
    ),
    ConversationItem(
        "Làm ơn nói chậm",
        "ゆっくり話してください",
        "一般",
        "依頼",
        "làm ơn",
        "依頼"
    ),
    ConversationItem(
        "Làm ơn chờ tôi một chút",
        "少し待ってください",
        "一般",
        "依頼",
        "làm ơn",
        "依頼"
    ),
    ConversationItem(
        "Chờ tôi một chút",
        "少し待ってください",
        "一般",
        "依頼",
        "軽い依頼",
        "待って"
    ),
    ConversationItem(
        "Đợi một chút nhé",
        "ちょっと待ってくださいね",
        "一般",
        "依頼",
        "軽い依頼",
        "待つ"
    ),
    ConversationItem(
        "Cho tôi một chút thời gian",
        "少し時間をください",
        "一般",
        "依頼",
        "軽い依頼",
        "時間をください"
    ),
    ConversationItem(
        "Bạn đợi tôi một chút nhé",
        "少し待ってくれますか？",
        "一般",
        "依頼",
        "依頼",
        "待ってもらう"
    ),
    ConversationItem(
        "Bạn nghe tôi một chút nhé",
        "少し聞いてもらえますか？",
        "一般",
        "依頼",
        "依頼",
        "聞いてもらう"
    ),
    ConversationItem(
        "Bạn xem giúp tôi nhé",
        "確認してもらえますか？",
        "一般",
        "依頼",
        "依頼",
        "助けて依頼"
    ),
    ConversationItem(
        "Bạn nói rõ hơn được không?",
        "もう少し詳しく言ってもらえますか？",
        "一般",
        "依頼",
        "説明依頼",
        "詳しく説明"
    ),
    ConversationItem(
        "Bạn nói chậm lại được không?",
        "もう少しゆっくり話してもらえますか？",
        "一般",
        "依頼",
        "説明依頼",
        "ゆっくり話して"
    ),
    ConversationItem(
        "Bạn giải thích thêm được không?",
        "もう少し説明してもらえますか？",
        "一般",
        "依頼",
        "説明依頼",
        "追加説明"
    ),
    ConversationItem(
        "Bạn có thể nói cụ thể hơn không?",
        "もう少し具体的に言ってもらえますか？",
        "一般",
        "依頼",
        "詳細依頼",
        "具体化"
    ),
    ConversationItem(
        "Bạn có thể giải thích rõ hơn không?",
        "もう少し詳しく説明してもらえますか？",
        "一般",
        "依頼",
        "詳細依頼",
        "詳細"
    ),
    ConversationItem(
        "Bạn có thể cho ví dụ không?",
        "例をあげてもらえますか？",
        "一般",
        "依頼",
        "詳細依頼",
        "例要求"
    ),
    ConversationItem(
        "Bạn kiểm tra lại giúp tôi nhé",
        "もう一度確認してもらえますか？",
        "一般",
        "依頼",
        "再確認依頼",
        "再確認"
    ),
    ConversationItem(
        "Bạn xem lại giúp tôi nhé",
        "もう一度見てもらえますか？",
        "一般",
        "依頼",
        "再確認依頼",
        "再確認"
    ),
    ConversationItem(
        "Bạn đọc lại giúp tôi nhé",
        "もう一度読んでもらえますか？",
        "一般",
        "依頼",
        "再確認依頼",
        "再確認"
    ),

    //
    // ✅ Part3 感謝・謝罪
    //
    ConversationItem(
        "Cảm ơn bạn",
        "ありがとうございます",
        "一般",
        "感謝・謝罪",
        "cảm ơn",
        "感謝"
    ),
    ConversationItem(
        "Cảm ơn bạn rất nhiều",
        "本当にありがとうございます",
        "一般",
        "感謝・謝罪",
        "cảm ơn",
        "感謝"
    ),
    ConversationItem(
        "Cảm ơn vì đã giúp tôi",
        "手伝ってくれてありがとうございます",
        "一般",
        "感謝・謝罪",
        "cảm ơn",
        "感謝"
    ),
    ConversationItem(
        "Xin lỗi, tôi đến muộn",
        "すみません、遅れました",
        "一般",
        "感謝・謝罪",
        "xin lỗi",
        "謝罪"
    ),
    ConversationItem(
        "Xin lỗi vì làm phiền",
        "ご迷惑をおかけしてすみません",
        "一般",
        "感謝・謝罪",
        "xin lỗi",
        "謝罪"
    ),
    ConversationItem(
        "Xin lỗi, tôi không hiểu",
        "すみません、理解できません",
        "一般",
        "感謝・謝罪",
        "xin lỗi",
        "謝罪"
    ),
    ConversationItem(
        "Xin lỗi, tôi bận",
        "すみません、忙しいです",
        "一般",
        "感謝・謝罪",
        "謝罪＋理由",
        "すみません＋理由"
    ),
    ConversationItem(
        "Xin lỗi, tôi không có thời gian",
        "すみません、時間がありません",
        "一般",
        "感謝・謝罪",
        "謝罪＋理由",
        "すみません＋理由"
    ),
    ConversationItem(
        "Xin lỗi, tôi không thể đi",
        "すみません、行けません",
        "一般",
        "感謝・謝罪",
        "謝罪＋理由",
        "すみません＋理由"
    ),
    ConversationItem(
        "Tôi xin lỗi vì đến trễ",
        "遅れてしまい申し訳ありません",
        "一般",
        "感謝・謝罪",
        "丁寧な謝罪",
        "遅刻謝罪"
    ),
    ConversationItem(
        "Tôi xin lỗi vì phản hồi chậm",
        "返信が遅くなり申し訳ありません",
        "一般",
        "感謝・謝罪",
        "丁寧な謝罪",
        "遅延謝罪"
    ),
    ConversationItem(
        "Tôi xin lỗi vì nhầm lẫn",
        "誤解があり申し訳ありません",
        "一般",
        "感謝・謝罪",
        "丁寧な謝罪",
        "ミス謝罪"
    ),
    ConversationItem(
        "Tôi hiểu rồi",
        "私は理解しました",
        "一般",
        "感謝・謝罪",
        "理解",
        "理解した"
    ),
    ConversationItem(
        "Tôi hiểu ý bạn",
        "私はあなたの言いたいことが分かります",
        "一般",
        "感謝・謝罪",
        "理解",
        "意味理解"
    ),
    ConversationItem(
        "Tôi hiểu vấn đề rồi",
        "問題は理解しました",
        "一般",
        "感謝・謝罪",
        "理解",
        "問題理解"
    ),
    ConversationItem(
        "Tôi không hiểu",
        "私は理解できません",
        "一般",
        "感謝・謝罪",
        "未理解",
        "理解できない"
    ),
    ConversationItem(
        "Tôi chưa hiểu lắm",
        "私はまだよく分かりません",
        "一般",
        "感謝・謝罪",
        "未理解",
        "部分理解"
    ),
    ConversationItem(
        "Tôi chưa hiểu rõ",
        "まだよく理解できていません",
        "一般",
        "感謝・謝罪",
        "未理解",
        "未理解"
    ),
    ConversationItem(
        "Bạn có hiểu không?",
        "あなたは理解していますか？",
        "一般",
        "感謝・謝罪",
        "理解確認",
        "理解してる？"
    ),
    ConversationItem(
        "Bạn có câu hỏi nào không?",
        "何か質問はありますか？",
        "一般",
        "感謝・謝罪",
        "理解確認",
        "質問確認"
    ),
    ConversationItem(
        "Bạn cần gì thêm không?",
        "他に何か必要ですか？",
        "一般",
        "感謝・謝罪",
        "理解確認",
        "追加確認"
    ),
    ConversationItem(
        "Không có gì",
        "どういたしまして",
        "一般",
        "感謝・謝罪",
        "感謝への返答",
        "感謝返し"
    ),
    ConversationItem(
        "Không sao đâu",
        "大丈夫です",
        "一般",
        "感謝・謝罪",
        "感謝への返答",
        "OK"
    ),
    ConversationItem(
        "Không vấn đề gì",
        "問題ありません",
        "一般",
        "感謝・謝罪",
        "感謝への返答",
        "問題ない"
    ),

    //
    // ✅ Part4 確認
    //
    ConversationItem(
        "Bạn có thời gian không?",
        "あなたは時間がありますか？",
        "一般",
        "確認",
        "確認",
        "時間ありますか"
    ),
    ConversationItem(
        "Bạn rảnh không?",
        "あなたは暇ですか？",
        "一般",
        "確認",
        "確認",
        "暇ですか"
    ),
    ConversationItem(
        "Bạn đang bận không?",
        "あなたは今忙しいですか？",
        "一般",
        "確認",
        "確認",
        "忙しい？"
    ),
    ConversationItem(
        "Bạn đang làm gì?",
        "あなたは今何をしていますか？",
        "一般",
        "確認",
        "状況確認",
        "何してる？"
    ),
    ConversationItem(
        "Bạn đang ở đâu?",
        "あなたは今どこにいますか？",
        "一般",
        "確認",
        "状況確認",
        "どこにいる？"
    ),
    ConversationItem(
        "Bạn đang ở chỗ nào?",
        "どこにいますか？",
        "一般",
        "確認",
        "状況確認",
        "場所"
    ),
    ConversationItem(
        "Bạn đến chưa?",
        "もう着きましたか？",
        "一般",
        "確認",
        "到着確認",
        "到着確認"
    ),
    ConversationItem(
        "Bạn đến rồi à?",
        "もう着いたんですか？",
        "一般",
        "確認",
        "到着確認",
        "到着確認"
    ),
    ConversationItem(
        "Bạn sắp đến chưa?",
        "もうすぐ着きますか？",
        "一般",
        "確認",
        "到着確認",
        "到着予定"
    ),
    ConversationItem(
        "Bạn xong chưa?",
        "もう終わりましたか？",
        "一般",
        "確認",
        "完了確認",
        "完了確認"
    ),
    ConversationItem(
        "Bạn xong rồi à?",
        "もう終わったんですか？",
        "一般",
        "確認",
        "完了確認",
        "完了確認"
    ),
    ConversationItem(
        "Bạn làm xong chưa?",
        "もう終わりましたか？",
        "一般",
        "確認",
        "完了確認",
        "完了確認"
    ),
    ConversationItem(
        "Bạn ăn chưa?",
        "もう食べましたか？",
        "一般",
        "確認",
        "完了確認",
        "食事確認"
    ),
    ConversationItem(
        "Bạn về chưa?",
        "もう帰りましたか？",
        "一般",
        "確認",
        "完了確認",
        "帰宅確認"
    ),
    ConversationItem(
        "Bạn đã nhận được chưa?",
        "もう受け取りましたか？",
        "一般",
        "確認",
        "完了確認",
        "受信確認"
    ),
    ConversationItem(
        "Bạn biết không?",
        "知っていますか？",
        "一般",
        "確認",
        "質問",
        "一般質問"
    ),
    ConversationItem(
        "Bạn biết ở đâu không?",
        "どこにあるか知っていますか？",
        "一般",
        "確認",
        "質問",
        "場所質問"
    ),
    ConversationItem(
        "Bạn biết chỗ này không?",
        "この場所を知っていますか？",
        "一般",
        "確認",
        "質問",
        "場所"
    ),
    ConversationItem(
        "Tôi không biết",
        "私は分かりません",
        "一般",
        "確認",
        "不明",
        "分からない"
    ),
    ConversationItem(
        "Tôi không rõ lắm",
        "私はあまりよく分かりません",
        "一般",
        "確認",
        "不明",
        "曖昧"
    ),
    ConversationItem(
        "Tôi không rõ",
        "よく分かりません",
        "一般",
        "確認",
        "不明",
        "曖昧"
    ),
    ConversationItem(
        "Tôi chưa chắc",
        "私はまだ確信がありません",
        "一般",
        "確認",
        "不確実",
        "不確実"
    ),
    ConversationItem(
        "Tôi không chắc lắm",
        "あまり確かではありません",
        "一般",
        "確認",
        "不確実",
        "確信がない"
    ),
    ConversationItem(
        "Tôi chưa chắc chắn",
        "まだはっきりしていません",
        "一般",
        "確認",
        "不確実",
        "不明確"
    ),
    ConversationItem(
        "Bạn có chắc không?",
        "本当に大丈夫ですか？",
        "一般",
        "確認",
        "確認",
        "確認する"
    ),
    ConversationItem(
        "Bạn nói đúng không?",
        "それで合っていますか？",
        "一般",
        "確認",
        "確認",
        "正しいか確認"
    ),
    ConversationItem(
        "Bạn ổn không?",
        "大丈夫ですか？",
        "一般",
        "確認",
        "確認",
        "体調確認"
    ),

    //
    // ✅ Part5 状態説明
    //
    ConversationItem(
        "Tôi bận bây giờ",
        "私は今忙しいです",
        "一般",
        "状態説明",
        "状態",
        "忙しい"
    ),
    ConversationItem(
        "Tôi đang bận chút",
        "少し今忙しいです",
        "一般",
        "状態説明",
        "状態",
        "忙しい"
    ),
    ConversationItem(
        "Tôi đang bận một chút",
        "少し忙しいです",
        "一般",
        "状態説明",
        "状態",
        "忙しい"
    ),
    ConversationItem(
        "Tôi đang rảnh",
        "私は今暇です",
        "一般",
        "状態説明",
        "状態",
        "暇です"
    ),
    ConversationItem(
        "Tôi rảnh bây giờ",
        "今は暇です",
        "一般",
        "状態説明",
        "状態",
        "現在暇"
    ),
    ConversationItem(
        "Giờ tôi rảnh",
        "今は空いています",
        "一般",
        "状態説明",
        "状態",
        "現在空き"
    ),
    ConversationItem(
        "Tôi không rảnh hôm nay",
        "私は今日は暇ではありません",
        "一般",
        "状態説明",
        "状態",
        "暇ではない"
    ),
    ConversationItem(
        "Tôi bận việc",
        "仕事で忙しいです",
        "一般",
        "状態説明",
        "状態",
        "仕事理由"
    ),
    ConversationItem(
        "Tôi đang họp",
        "私は会議中です",
        "一般",
        "状態説明",
        "状態",
        "会議中"
    ),
    ConversationItem(
        "Tôi đang làm việc",
        "私は仕事をしています",
        "一般",
        "状態説明",
        "現在進行",
        "〜している"
    ),
    ConversationItem(
        "Tôi đang ăn",
        "私は食事をしています",
        "一般",
        "状態説明",
        "現在進行",
        "〜している"
    ),
    ConversationItem(
        "Tôi đang nghỉ",
        "私は休んでいます",
        "一般",
        "状態説明",
        "現在進行",
        "〜している"
    ),
    ConversationItem(
        "Tôi đang trên đường",
        "私は今向かっています",
        "一般",
        "状態説明",
        "移動状況",
        "向かっている"
    ),
    ConversationItem(
        "Tôi đang ở ga",
        "私は今駅にいます",
        "一般",
        "状態説明",
        "移動状況",
        "駅にいる"
    ),
    ConversationItem(
        "Tôi đang ở văn phòng",
        "私はオフィスにいます",
        "一般",
        "状態説明",
        "移動状況",
        "オフィスにいる"
    ),
    ConversationItem(
        "Tôi đến rồi",
        "私はもう着きました",
        "一般",
        "状態説明",
        "到着",
        "到着した"
    ),
    ConversationItem(
        "Tôi sắp đến",
        "もうすぐ着きます",
        "一般",
        "状態説明",
        "到着",
        "もうすぐ"
    ),
    ConversationItem(
        "Tôi vừa đến",
        "今ちょうど着きました",
        "一般",
        "状態説明",
        "到着",
        "今着いた"
    ),
    ConversationItem(
        "Tôi rảnh sau 5 giờ",
        "5時以降は空いています",
        "一般",
        "状態説明",
        "予定",
        "時間指定"
    ),
    ConversationItem(
        "Tôi rảnh ngày mai",
        "明日は空いています",
        "一般",
        "状態説明",
        "予定",
        "日付"
    ),
    ConversationItem(
        "Tôi rảnh cuối tuần",
        "週末は空いています",
        "一般",
        "状態説明",
        "予定",
        "週末"
    ),
    ConversationItem(
        "Tôi sẽ về sớm",
        "私は早く帰ります",
        "一般",
        "状態説明",
        "予定",
        "早く帰る"
    ),
    ConversationItem(
        "Tôi sẽ về muộn",
        "私は遅く帰ります",
        "一般",
        "状態説明",
        "予定",
        "遅く帰る"
    ),
    ConversationItem(
        "Tôi sẽ về ngay",
        "私はすぐ帰ります",
        "一般",
        "状態説明",
        "予定",
        "すぐ帰る"
    ),
    ConversationItem(
        "Tôi đến sớm",
        "私は早く着きました",
        "一般",
        "状態説明",
        "時間",
        "早着"
    ),
    ConversationItem(
        "Tôi đến đúng giờ",
        "時間通りに来ました",
        "一般",
        "状態説明",
        "時間",
        "時間どおり"
    ),
    ConversationItem(
        "Tôi đến trễ một chút",
        "少し遅れて来ました",
        "一般",
        "状態説明",
        "時間",
        "遅れ"
    ),
    ConversationItem(
        "Tôi mệt quá",
        "私はとても疲れています",
        "一般",
        "状態説明",
        "体調",
        "疲れている"
    ),
    ConversationItem(
        "Tôi đói",
        "私はお腹が空いています",
        "一般",
        "状態説明",
        "体調",
        "空腹"
    ),
    ConversationItem(
        "Tôi không khỏe",
        "私は体調がよくありません",
        "一般",
        "状態説明",
        "体調",
        "体調不良"
    ),

    //
    // ✅ Part6 意見・同意・断り
    //
    ConversationItem(
        "Xin lỗi, tôi không đi được",
        "すみません、行けません",
        "一般",
        "意見・同意・断り",
        "断り",
        "断る"
    ),
    ConversationItem(
        "Xin lỗi, tôi không làm được",
        "すみません、できません",
        "一般",
        "意見・同意・断り",
        "断り",
        "断る"
    ),
    ConversationItem(
        "Xin lỗi, tôi bận nên không giúp được",
        "すみません、忙しいので手伝えません",
        "一般",
        "意見・同意・断り",
        "断り",
        "断る"
    ),
    ConversationItem(
        "Tôi không đi được",
        "私は行けません",
        "一般",
        "意見・同意・断り",
        "できない",
        "できない"
    ),
    ConversationItem(
        "Tôi không làm được",
        "私はできません",
        "一般",
        "意見・同意・断り",
        "できない",
        "できない"
    ),
    ConversationItem(
        "Tôi không giúp được",
        "私は手伝えません",
        "一般",
        "意見・同意・断り",
        "できない",
        "できない"
    ),
    ConversationItem(
        "Tôi đồng ý",
        "私は同意します",
        "一般",
        "意見・同意・断り",
        "同意",
        "同意"
    ),
    ConversationItem(
        "Tôi đồng ý với bạn",
        "私はあなたに賛成です",
        "一般",
        "意見・同意・断り",
        "同意",
        "賛成"
    ),
    ConversationItem(
        "Tôi cũng nghĩ vậy",
        "私もそう思います",
        "一般",
        "意見・同意・断り",
        "同意",
        "同意強調"
    ),
    ConversationItem(
        "Tôi đồng ý làm việc này",
        "私はこの仕事をやることに同意します",
        "一般",
        "意見・同意・断り",
        "承諾",
        "承諾"
    ),
    ConversationItem(
        "Tôi đồng ý giúp bạn",
        "私はあなたを手伝うことに同意します",
        "一般",
        "意見・同意・断り",
        "承諾",
        "承諾"
    ),
    ConversationItem(
        "Được, tôi làm",
        "いいですよ、やります",
        "一般",
        "意見・同意・断り",
        "承諾",
        "軽い承諾"
    ),
    ConversationItem(
        "Tôi nghĩ vậy",
        "私はそう思います",
        "一般",
        "意見・同意・断り",
        "意見",
        "考え"
    ),
    ConversationItem(
        "Tôi nghĩ là được",
        "大丈夫だと思います",
        "一般",
        "意見・同意・断り",
        "意見",
        "肯定意見"
    ),
    ConversationItem(
        "Tôi nghĩ không được",
        "難しいと思います",
        "一般",
        "意見・同意・断り",
        "意見",
        "否定意見"
    ),
    ConversationItem(
        "Bạn nghĩ sao?",
        "どう思いますか？",
        "一般",
        "意見・同意・断り",
        "意見質問",
        "意見を聞く"
    ),
    ConversationItem(
        "Bạn thấy thế nào?",
        "どう思いますか？",
        "一般",
        "意見・同意・断り",
        "意見質問",
        "印象確認"
    ),
    ConversationItem(
        "Bạn có ý kiến gì không?",
        "何か意見はありますか？",
        "一般",
        "意見・同意・断り",
        "意見質問",
        "意見"
    ),
    ConversationItem(
        "Có lẽ được",
        "たぶん大丈夫です",
        "一般",
        "意見・同意・断り",
        "推測",
        "たぶん"
    ),
    ConversationItem(
        "Có lẽ đúng",
        "たぶん正しいです",
        "一般",
        "意見・同意・断り",
        "推測",
        "推測"
    ),
    ConversationItem(
        "Chắc được",
        "たぶん大丈夫です",
        "一般",
        "意見・同意・断り",
        "推測",
        "たぶんOK"
    ),
    ConversationItem(
        "Có lẽ không được",
        "たぶん無理です",
        "一般",
        "意見・同意・断り",
        "難しい見込み",
        "たぶん無理"
    ),
    ConversationItem(
        "Có vẻ khó",
        "難しそうです",
        "一般",
        "意見・同意・断り",
        "難しい見込み",
        "難しそう"
    ),
    ConversationItem(
        "Cái này hơi khó",
        "これは少し難しいです",
        "一般",
        "意見・同意・断り",
        "難しい見込み",
        "やや難しい"
    ),
    ConversationItem(
        "Có vẻ được",
        "いけそうです",
        "一般",
        "意見・同意・断り",
        "可能性",
        "見た感じOK"
    ),
    ConversationItem(
        "Có thể được",
        "大丈夫そうです",
        "一般",
        "意見・同意・断り",
        "可能性",
        "可能"
    ),
    ConversationItem(
        "Việc này ổn",
        "これはいい感じです",
        "一般",
        "意見・同意・断り",
        "可能性",
        "OK"
    ),
    ConversationItem(
        "Tôi không muốn làm việc này",
        "この仕事はやりたくありません",
        "一般",
        "意見・同意・断り",
        "やんわり拒否",
        "やりたくない"
    ),
    ConversationItem(
        "Tôi không muốn đi hôm nay",
        "今日は行きたくありません",
        "一般",
        "意見・同意・断り",
        "やんわり拒否",
        "行きたくない"
    ),
    ConversationItem(
        "Tôi không thích cách này",
        "このやり方は好きではありません",
        "一般",
        "意見・同意・断り",
        "やんわり拒否",
        "気に入らない"
    ),

    //
    // ✅ Part7 提案・意思表示
    //
    ConversationItem(
        "Tôi sẽ làm sau",
        "私は後でやります",
        "仕事",
        "提案・意思表示",
        "後で対応",
        "後でやる"
    ),
    ConversationItem(
        "Tôi sẽ gọi lại",
        "私は折り返し電話します",
        "仕事",
        "提案・意思表示",
        "後で対応",
        "後で対応"
    ),
    ConversationItem(
        "Tôi sẽ trả lời sau",
        "私は後で返事します",
        "仕事",
        "提案・意思表示",
        "後で対応",
        "後で返す"
    ),
    ConversationItem(
        "Tôi quay lại sau",
        "後で戻ります",
        "仕事",
        "提案・意思表示",
        "後で対応",
        "後で戻る"
    ),
    ConversationItem(
        "Tôi liên lạc lại sau",
        "後で連絡します",
        "仕事",
        "提案・意思表示",
        "後で対応",
        "後で連絡"
    ),
    ConversationItem(
        "Tôi nói với bạn sau",
        "後で話します",
        "仕事",
        "提案・意思表示",
        "後で対応",
        "後で話す"
    ),
    ConversationItem(
        "Tôi sẽ giúp bạn sau",
        "私は後であなたを手伝います",
        "仕事",
        "提案・意思表示",
        "代替提案",
        "後でやる"
    ),
    ConversationItem(
        "Tôi sẽ làm vào ngày mai",
        "私は明日やります",
        "仕事",
        "提案・意思表示",
        "代替提案",
        "別タイミング"
    ),
    ConversationItem(
        "Tôi sẽ gửi lại sau",
        "私は後で送り直します",
        "仕事",
        "提案・意思表示",
        "代替提案",
        "後で対応"
    ),
    ConversationItem(
        "Tôi sẽ gọi cho bạn sau",
        "後であなたに電話します",
        "仕事",
        "提案・意思表示",
        "連絡",
        "電話連絡"
    ),
    ConversationItem(
        "Tôi sẽ nhắn tin cho bạn",
        "私はメッセージを送ります",
        "仕事",
        "提案・意思表示",
        "連絡",
        "メッセージ"
    ),
    ConversationItem(
        "Tôi sẽ liên lạc lại",
        "後で連絡します",
        "仕事",
        "提案・意思表示",
        "連絡",
        "再連絡"
    ),
    ConversationItem(
        "Tôi sẽ cố gắng",
        "私は頑張ります",
        "仕事",
        "提案・意思表示",
        "意志",
        "努力"
    ),
    ConversationItem(
        "Tôi sẽ thử",
        "私は試してみます",
        "仕事",
        "提案・意思表示",
        "意志",
        "試す"
    ),
    ConversationItem(
        "Tôi sẽ kiểm tra lại",
        "私は確認します",
        "仕事",
        "提案・意思表示",
        "意志",
        "確認する"
    ),
    ConversationItem(
        "Tôi sẽ bắt đầu bây giờ",
        "今から始めます",
        "仕事",
        "提案・意思表示",
        "開始",
        "スタート"
    ),
    ConversationItem(
        "Tôi sẽ làm tiếp",
        "続きをやります",
        "仕事",
        "提案・意思表示",
        "開始",
        "続ける"
    ),
    ConversationItem(
        "Tôi có thể bắt đầu",
        "始められます",
        "仕事",
        "提案・意思表示",
        "開始",
        "開始可能"
    ),
    ConversationItem(
        "Tôi đến ngay",
        "すぐ行きます",
        "仕事",
        "提案・意思表示",
        "即時対応",
        "すぐ行く"
    ),
    ConversationItem(
        "Tôi làm ngay",
        "すぐやります",
        "仕事",
        "提案・意思表示",
        "即時対応",
        "すぐ対応"
    ),
    ConversationItem(
        "Tôi trả lời ngay",
        "すぐ返事します",
        "仕事",
        "提案・意思表示",
        "即時対応",
        "即返答"
    ),
    ConversationItem(
        "Bạn có muốn không?",
        "欲しいですか？",
        "仕事",
        "提案・意思表示",
        "提案確認",
        "欲しいか"
    ),
    ConversationItem(
        "Bạn có cần không?",
        "必要ですか？",
        "仕事",
        "提案・意思表示",
        "提案確認",
        "必要か"
    ),
    ConversationItem(
        "Bạn có đi không?",
        "行きますか？",
        "仕事",
        "提案・意思表示",
        "提案確認",
        "行くか"
    ),
    ConversationItem(
        "Chúng ta đi thôi",
        "行きましょう",
        "仕事",
        "提案・意思表示",
        "提案",
        "一緒に行く"
    ),
    ConversationItem(
        "Chúng ta làm nhé",
        "やりましょう",
        "仕事",
        "提案・意思表示",
        "提案",
        "一緒にやる"
    ),
    ConversationItem(
        "Chúng ta bắt đầu nhé",
        "始めましょう",
        "仕事",
        "提案・意思表示",
        "提案",
        "開始"
    ),

    //
    // ✅ Part8 進捗・修正
    //
    ConversationItem(
        "Bạn làm đến đâu rồi?",
        "どこまで終わりましたか？",
        "仕事",
        "進捗・修正",
        "進捗確認",
        "進行状況"
    ),
    ConversationItem(
        "Bạn làm xong chưa?",
        "もう終わりましたか？",
        "仕事",
        "進捗・修正",
        "進捗確認",
        "完了確認"
    ),
    ConversationItem(
        "Tiến độ thế nào rồi?",
        "進捗はどうですか？",
        "仕事",
        "進捗・修正",
        "進捗確認",
        "進行状況"
    ),
    ConversationItem(
        "Tôi gần xong rồi",
        "もうすぐ終わります",
        "仕事",
        "進捗・修正",
        "進捗報告",
        "もうすぐ"
    ),
    ConversationItem(
        "Tôi xong rồi",
        "もう終わりました",
        "仕事",
        "進捗・修正",
        "進捗報告",
        "完了"
    ),
    ConversationItem(
        "Tôi chưa xong",
        "まだ終わっていません",
        "仕事",
        "進捗・修正",
        "進捗報告",
        "未完了"
    ),
    ConversationItem(
        "Tôi xác nhận rồi",
        "確認しました",
        "仕事",
        "進捗・修正",
        "確認完了",
        "確認OK"
    ),
    ConversationItem(
        "Tôi đã kiểm tra",
        "確認しました",
        "仕事",
        "進捗・修正",
        "確認完了",
        "チェック済み"
    ),
    ConversationItem(
        "Tôi đã xem xong",
        "確認が終わりました",
        "仕事",
        "進捗・修正",
        "確認完了",
        "完了報告"
    ),
    ConversationItem(
        "Để tôi xem",
        "ちょっと確認します",
        "仕事",
        "進捗・修正",
        "確認対応",
        "確認する"
    ),
    ConversationItem(
        "Để tôi kiểm tra",
        "確認させてください",
        "仕事",
        "進捗・修正",
        "確認対応",
        "チェックする"
    ),
    ConversationItem(
        "Tôi sẽ xem lại",
        "もう一度確認します",
        "仕事",
        "進捗・修正",
        "確認対応",
        "再確認"
    ),
    ConversationItem(
        "Tôi sẽ chuẩn bị",
        "準備します",
        "仕事",
        "進捗・修正",
        "対応",
        "準備"
    ),
    ConversationItem(
        "Tôi sẽ sắp xếp",
        "手配します",
        "仕事",
        "進捗・修正",
        "対応",
        "手配"
    ),
    ConversationItem(
        "Tôi sẽ tổ chức lại",
        "整理します",
        "仕事",
        "進捗・修正",
        "対応",
        "整理"
    ),
    ConversationItem(
        "Tôi sẽ tìm",
        "探します",
        "仕事",
        "進捗・修正",
        "対応",
        "探す行動"
    ),
    ConversationItem(
        "Tôi sẽ hỏi người khác",
        "他の人に聞きます",
        "仕事",
        "進捗・修正",
        "対応",
        "別手段"
    ),
    ConversationItem(
        "Tôi sẽ kiểm tra kỹ hơn",
        "もっと詳しく確認します",
        "仕事",
        "進捗・修正",
        "対応",
        "詳細確認"
    ),
    ConversationItem(
        "Tôi sẽ sửa lại",
        "修正します",
        "仕事",
        "進捗・修正",
        "修正",
        "修正対応"
    ),
    ConversationItem(
        "Tôi sẽ làm lại",
        "やり直します",
        "仕事",
        "進捗・修正",
        "修正",
        "再作業"
    ),
    ConversationItem(
        "Tôi sẽ cập nhật lại",
        "更新します",
        "仕事",
        "進捗・修正",
        "修正",
        "更新対応"
    ),
    ConversationItem(
        "Bạn sửa giúp tôi được không?",
        "修正してもらえますか？",
        "仕事",
        "進捗・修正",
        "修正依頼",
        "修正依頼"
    ),
    ConversationItem(
        "Bạn làm lại giúp tôi được không?",
        "やり直してもらえますか？",
        "仕事",
        "進捗・修正",
        "修正依頼",
        "再作業依頼"
    ),
    ConversationItem(
        "Bạn cập nhật giúp tôi được không?",
        "更新してもらえますか？",
        "仕事",
        "進捗・修正",
        "修正依頼",
        "更新依頼"
    ),
    ConversationItem(
        "Tôi gửi nhầm",
        "間違って送りました",
        "仕事",
        "進捗・修正",
        "ミス",
        "送信ミス"
    ),
    ConversationItem(
        "Tôi làm sai rồi",
        "間違えました",
        "仕事",
        "進捗・修正",
        "ミス",
        "ミス報告"
    ),
    ConversationItem(
        "Tôi nhầm rồi",
        "間違いました",
        "仕事",
        "進捗・修正",
        "ミス",
        "軽いミス"
    ),

    //
    // ✅ Part9 受信・待機
    //
    ConversationItem(
        "Tôi nhận được rồi",
        "受け取りました",
        "仕事",
        "受信・待機",
        "受信",
        "受信完了"
    ),
    ConversationItem(
        "Tôi đã nhận",
        "受け取りました",
        "仕事",
        "受信・待機",
        "受信",
        "受信完了"
    ),
    ConversationItem(
        "Tôi thấy rồi",
        "確認しました",
        "仕事",
        "受信・待機",
        "受信",
        "確認"
    ),
    ConversationItem(
        "Bạn nhận được chưa?",
        "受け取りましたか？",
        "仕事",
        "受信・待機",
        "受信確認",
        "受信確認"
    ),
    ConversationItem(
        "Bạn đã nhận được chưa?",
        "もう受け取りましたか？",
        "仕事",
        "受信・待機",
        "受信確認",
        "受信確認"
    ),
    ConversationItem(
        "Bạn thấy chưa?",
        "もう見ましたか？",
        "仕事",
        "受信・待機",
        "受信確認",
        "確認"
    ),
    ConversationItem(
        "Tôi đang chờ bạn",
        "私はあなたを待っています",
        "仕事",
        "受信・待機",
        "待機",
        "待機"
    ),
    ConversationItem(
        "Tôi đang đợi ở đây",
        "私はここで待っています",
        "仕事",
        "受信・待機",
        "待機",
        "待機"
    ),
    ConversationItem(
        "Tôi đang đợi bên ngoài",
        "私は外で待っています",
        "仕事",
        "受信・待機",
        "待機",
        "待機"
    ),
    ConversationItem(
        "Bạn đợi lâu chưa?",
        "長く待ちましたか？",
        "仕事",
        "受信・待機",
        "待機確認",
        "待ち時間"
    ),
    ConversationItem(
        "Bạn đợi bao lâu rồi?",
        "どのくらい待ちましたか？",
        "仕事",
        "受信・待機",
        "待機確認",
        "時間"
    ),
    ConversationItem(
        "Bạn chờ lâu không?",
        "そんなに待っていませんか？",
        "仕事",
        "受信・待機",
        "待機確認",
        "軽い確認"
    ),
    ConversationItem(
        "Tôi đi cùng bạn",
        "私はあなたと一緒に行きます",
        "仕事",
        "受信・待機",
        "同行",
        "一緒に行動"
    ),
    ConversationItem(
        "Tôi về cùng bạn",
        "私はあなたと一緒に帰ります",
        "仕事",
        "受信・待機",
        "同行",
        "一緒に帰る"
    ),
    ConversationItem(
        "Tôi làm cùng bạn",
        "私はあなたと一緒にやります",
        "仕事",
        "受信・待機",
        "同行",
        "一緒にやる"
    ),
    ConversationItem(
        "Bạn về cùng tôi không?",
        "一緒に帰りますか？",
        "仕事",
        "受信・待機",
        "同行提案",
        "一緒に帰る"
    ),
    ConversationItem(
        "Chúng ta gặp nhau nhé",
        "会いましょう",
        "仕事",
        "受信・待機",
        "同行提案",
        "会う提案"
    ),
    ConversationItem(
        "Bạn có thể gặp tôi không?",
        "会ってもらえますか？",
        "仕事",
        "受信・待機",
        "同行提案",
        "面会"
    ),
    ConversationItem(
        "Được rồi",
        "わかりました",
        "仕事",
        "受信・待機",
        "会話締め",
        "OK"
    ),
    ConversationItem(
        "OK, tôi làm ngay",
        "はい、すぐやります",
        "仕事",
        "受信・待機",
        "会話締め",
        "対応する"
    ),
    ConversationItem(
        "Tôi biết rồi",
        "分かりました",
        "仕事",
        "受信・待機",
        "会話締め",
        "理解"
    ),
    ConversationItem(
        "Không sao đâu",
        "大丈夫です",
        "仕事",
        "受信・待機",
        "安心",
        "大丈夫"
    ),
    ConversationItem(
        "Không vấn đề gì",
        "問題ありません",
        "仕事",
        "受信・待機",
        "安心",
        "問題ない"
    ),
    ConversationItem(
        "Ổn rồi",
        "もう大丈夫です",
        "仕事",
        "受信・待機",
        "安心",
        "解決"
    ),

    //
    // ✅ Part10 報連相
    //
    ConversationItem(
        "Tôi sẽ báo cáo ngay",
        "すぐ報告します",
        "仕事",
        "報連相",
        "報告",
        "報告する"
    ),
    ConversationItem(
        "Tôi đã báo cáo rồi",
        "もう報告しました",
        "仕事",
        "報連相",
        "報告",
        "報告済み"
    ),
    ConversationItem(
        "Tôi đang làm báo cáo",
        "報告書を作成しています",
        "仕事",
        "報連相",
        "報告",
        "報告作成"
    ),
    ConversationItem(
        "Tôi sẽ thông báo cho mọi người",
        "みんなに連絡します",
        "仕事",
        "報連相",
        "連絡",
        "周知する"
    ),
    ConversationItem(
        "Tôi đã thông báo rồi",
        "もう連絡しました",
        "仕事",
        "報連相",
        "連絡",
        "連絡済み"
    ),
    ConversationItem(
        "Tôi sẽ liên hệ lại",
        "改めて連絡します",
        "仕事",
        "報連相",
        "連絡",
        "再連絡"
    ),
    ConversationItem(
        "Tôi cần trao đổi việc này",
        "この件を相談したいです",
        "仕事",
        "報連相",
        "相談",
        "相談する"
    ),
    ConversationItem(
        "Chúng ta cần trao đổi thêm",
        "もう少し相談が必要です",
        "仕事",
        "報連相",
        "相談",
        "追加相談"
    ),
    ConversationItem(
        "Tôi muốn hỏi ý kiến của bạn",
        "あなたの意見を聞きたいです",
        "仕事",
        "報連相",
        "相談",
        "意見確認"
    ),

    //
    // ✅ Part11 会議
    //
    ConversationItem(
        "Tôi đang họp Teams",
        "Teams会議中です",
        "仕事",
        "会議",
        "会議",
        "会議中"
    ),
    ConversationItem(
        "Cuộc họp sắp bắt đầu",
        "会議がもうすぐ始まります",
        "仕事",
        "会議",
        "会議",
        "開始前"
    ),
    ConversationItem(
        "Cuộc họp đã kết thúc",
        "会議は終了しました",
        "仕事",
        "会議",
        "会議",
        "終了"
    ),
    ConversationItem(
        "Bạn nghe rõ không?",
        "聞こえますか？",
        "仕事",
        "会議",
        "オンライン会議",
        "音声確認"
    ),
    ConversationItem(
        "Tôi nghe rõ",
        "よく聞こえます",
        "仕事",
        "会議",
        "オンライン会議",
        "音声OK"
    ),
    ConversationItem(
        "Tôi nghe không rõ",
        "よく聞こえません",
        "仕事",
        "会議",
        "オンライン会議",
        "音声不良"
    ),
    ConversationItem(
        "Bạn thấy màn hình không?",
        "画面は見えますか？",
        "仕事",
        "会議",
        "画面共有",
        "画面確認"
    ),
    ConversationItem(
        "Tôi thấy màn hình rồi",
        "画面が見えています",
        "仕事",
        "会議",
        "画面共有",
        "画面OK"
    ),
    ConversationItem(
        "Tôi không thấy màn hình",
        "画面が見えません",
        "仕事",
        "会議",
        "画面共有",
        "画面不良"
    ),

    //
    // ✅ Part12 工場・設備・品質
    //
    ConversationItem(
        "Máy đang dừng",
        "機械が停止しています",
        "仕事",
        "工場・設備・品質",
        "設備",
        "停止"
    ),
    ConversationItem(
        "Máy đang hoạt động",
        "機械は稼働中です",
        "仕事",
        "工場・設備・品質",
        "設備",
        "稼働中"
    ),
    ConversationItem(
        "Tôi sẽ kiểm tra máy",
        "設備を確認します",
        "仕事",
        "工場・設備・品質",
        "設備",
        "設備確認"
    ),
    ConversationItem(
        "Có vấn đề với máy",
        "設備に問題があります",
        "仕事",
        "工場・設備・品質",
        "設備異常",
        "設備異常"
    ),
    ConversationItem(
        "Máy có lỗi",
        "設備に異常があります",
        "仕事",
        "工場・設備・品質",
        "設備異常",
        "故障"
    ),
    ConversationItem(
        "Tôi đang xử lý sự cố",
        "トラブル対応中です",
        "仕事",
        "工場・設備・品質",
        "設備異常",
        "対応中"
    ),
    ConversationItem(
        "Sản phẩm này bị lỗi",
        "この製品は不良です",
        "仕事",
        "工場・設備・品質",
        "品質",
        "不良品"
    ),
    ConversationItem(
        "Chúng tôi đang kiểm tra chất lượng",
        "品質を確認しています",
        "仕事",
        "工場・設備・品質",
        "品質",
        "品質確認"
    ),
    ConversationItem(
        "Chất lượng đã được xác nhận",
        "品質確認済みです",
        "仕事",
        "工場・設備・品質",
        "品質",
        "品質OK"
    ),

    //
    // ✅ Part13 イベント
    //
    ConversationItem(
        "Tôi đang ở trước sân khấu",
        "ステージ前にいます",
        "イベント",
        "イベント",
        "待ち合わせ",
        "現在地"
    ),
    ConversationItem(
        "Bạn đang ở đâu?",
        "どこにいますか？",
        "イベント",
        "イベント",
        "待ち合わせ",
        "場所確認"
    ),
    ConversationItem(
        "Tôi thấy bạn rồi",
        "見つけました",
        "イベント",
        "イベント",
        "待ち合わせ",
        "合流"
    ),
    ConversationItem(
        "Hôm nay có sự kiện gì?",
        "今日は何のイベントがありますか？",
        "イベント",
        "イベント",
        "確認",
        "イベント確認"
    ),
    ConversationItem(
        "Sự kiện này là gì?",
        "このイベントは何ですか？",
        "イベント",
        "イベント",
        "確認",
        "イベント内容"
    ),
    ConversationItem(
        "Đây là lễ hội gì?",
        "これは何のお祭りですか？",
        "イベント",
        "イベント",
        "確認",
        "イベント内容"
    ),
    ConversationItem(
        "Sự kiện bắt đầu lúc mấy giờ?",
        "イベントは何時に始まりますか？",
        "イベント",
        "イベント",
        "時間確認",
        "開始時間"
    ),
    ConversationItem(
        "Sự kiện kết thúc lúc mấy giờ?",
        "イベントは何時に終わりますか？",
        "イベント",
        "イベント",
        "時間確認",
        "終了時間"
    ),
    ConversationItem(
        "Chương trình còn bao lâu nữa?",
        "あとどれくらい続きますか？",
        "イベント",
        "イベント",
        "時間確認",
        "残り時間"
    ),
    ConversationItem(
        "Tôi muốn chụp ảnh ở đây",
        "ここで写真を撮りたいです",
        "イベント",
        "イベント",
        "写真撮影",
        "写真"
    ),
    ConversationItem(
        "Bạn chụp ảnh giúp tôi được không?",
        "写真を撮ってもらえますか？",
        "イベント",
        "イベント",
        "写真撮影",
        "撮影依頼"
    ),
    ConversationItem(
        "Chúng ta chụp ảnh cùng nhau nhé",
        "一緒に写真を撮りましょう",
        "イベント",
        "イベント",
        "写真撮影",
        "記念撮影"
    ),
    ConversationItem(
        "Đông người quá",
        "人が多いですね",
        "イベント",
        "イベント",
        "感想",
        "人が多い"
    ),
    ConversationItem(
        "Không khí rất sôi động",
        "とても盛り上がっていますね",
        "イベント",
        "イベント",
        "感想",
        "盛り上がり"
    ),
    ConversationItem(
        "Tôi thích không khí ở đây",
        "ここの雰囲気が好きです",
        "イベント",
        "イベント",
        "感想",
        "雰囲気"
    ),
    ConversationItem(
        "Chúng ta đi xem nhé",
        "見に行きましょう",
        "イベント",
        "イベント",
        "誘う",
        "イベント"
    ),
    ConversationItem(
        "Bạn muốn đi cùng không?",
        "一緒に行きますか？",
        "イベント",
        "イベント",
        "誘う",
        "同行"
    ),
    ConversationItem(
        "Đi dạo một chút nhé",
        "少し散歩しましょう",
        "イベント",
        "イベント",
        "誘う",
        "散歩"
    ),

    //
    // ✅ Part14 ローカル食堂・屋台
    //
    ConversationItem(
        "Món nào ngon nhất?",
        "おすすめは何ですか？",
        "食事",
        "屋台",
        "注文",
        "おすすめ"
    ),
    ConversationItem(
        "Tôi muốn gọi món này",
        "これを注文したいです",
        "食事",
        "屋台",
        "注文",
        "注文"
    ),
    ConversationItem(
        "Cho tôi một phần phở",
        "フォーを一つください",
        "食事",
        "屋台",
        "注文",
        "注文"
    ),
    ConversationItem(
        "Món này có cay không?",
        "この料理は辛いですか？",
        "食事",
        "屋台",
        "味確認",
        "辛さ"
    ),
    ConversationItem(
        "Món này có ngọt không?",
        "この料理は甘いですか？",
        "食事",
        "屋台",
        "味確認",
        "味"
    ),
    ConversationItem(
        "Món này có ngon không?",
        "この料理はおいしいですか？",
        "食事",
        "屋台",
        "味確認",
        "味"
    ),
    ConversationItem(
        "Không lấy rau mùi nhé",
        "パクチー抜きでお願いします",
        "食事",
        "屋台",
        "注文調整",
        "パクチー抜き"
    ),
    ConversationItem(
        "Ít cay thôi",
        "辛さ控えめでお願いします",
        "食事",
        "屋台",
        "注文調整",
        "辛さ調整"
    ),
    ConversationItem(
        "Không cho đá nhé",
        "氷は入れないでください",
        "食事",
        "屋台",
        "注文調整",
        "氷なし"
    ),
    ConversationItem(
        "Cho tôi mang về",
        "持ち帰りでお願いします",
        "食事",
        "屋台",
        "テイクアウト",
        "持ち帰り"
    ),
    ConversationItem(
        "Tôi ăn ở đây",
        "ここで食べます",
        "食事",
        "屋台",
        "テイクアウト",
        "店内飲食"
    ),
    ConversationItem(
        "Gói lại giúp tôi nhé",
        "包んでください",
        "食事",
        "屋台",
        "テイクアウト",
        "持ち帰り準備"
    ),
    ConversationItem(
        "Ngon quá",
        "とてもおいしいです",
        "食事",
        "屋台",
        "感想",
        "おいしい"
    ),
    ConversationItem(
        "Món này rất ngon",
        "この料理はとてもおいしいです",
        "食事",
        "屋台",
        "感想",
        "評価"
    ),
    ConversationItem(
        "Tôi thích món này",
        "この料理が好きです",
        "食事",
        "屋台",
        "感想",
        "好み"
    ),
    ConversationItem(
        "Hết bao nhiêu tiền?",
        "いくらですか？",
        "食事",
        "屋台",
        "支払い",
        "金額確認"
    ),
    ConversationItem(
        "Tôi trả bằng tiền mặt",
        "現金で払います",
        "食事",
        "屋台",
        "支払い",
        "支払い"
    ),
    ConversationItem(
        "Tiền thừa của tôi đâu?",
        "おつりはありますか？",
        "食事",
        "屋台",
        "支払い",
        "おつり"
    ),

    //
    // ✅ Part15 レストラン
    //
    ConversationItem(
        "Tôi muốn đặt bàn",
        "席を予約したいです",
        "食事",
        "レストラン",
        "予約",
        "予約"
    ),
    ConversationItem(
        "Tôi đặt bàn cho hai người",
        "2人で予約しました",
        "食事",
        "レストラン",
        "予約",
        "人数"
    ),
    ConversationItem(
        "Tôi có đặt bàn dưới tên Yamada",
        "山田の名前で予約しています",
        "食事",
        "レストラン",
        "予約",
        "予約確認"
    ),
    ConversationItem(
        "Còn bàn không?",
        "席はありますか？",
        "食事",
        "レストラン",
        "入店",
        "空席確認"
    ),
    ConversationItem(
        "Cho tôi bàn cho hai người",
        "2人席をお願いします",
        "食事",
        "レストラン",
        "入店",
        "席依頼"
    ),
    ConversationItem(
        "Tôi có đặt bàn",
        "予約しています",
        "食事",
        "レストラン",
        "入店",
        "予約"
    ),
    ConversationItem(
        "Cho tôi xem thực đơn",
        "メニューを見せてください",
        "食事",
        "レストラン",
        "注文",
        "メニュー"
    ),
    ConversationItem(
        "Tôi muốn gọi món",
        "注文をお願いします",
        "食事",
        "レストラン",
        "注文",
        "注文"
    ),
    ConversationItem(
        "Cho tôi món này",
        "これをください",
        "食事",
        "レストラン",
        "注文",
        "料理指定"
    ),
    ConversationItem(
        "Cho tôi thêm nước",
        "お水を追加してください",
        "食事",
        "レストラン",
        "追加注文",
        "水"
    ),
    ConversationItem(
        "Cho tôi thêm cơm",
        "ご飯を追加してください",
        "食事",
        "レストラン",
        "追加注文",
        "追加注文"
    ),
    ConversationItem(
        "Cho tôi thêm một phần nữa",
        "もう一人前ください",
        "食事",
        "レストラン",
        "追加注文",
        "追加"
    ),
    ConversationItem(
        "Tính tiền giúp tôi",
        "お会計をお願いします",
        "食事",
        "レストラン",
        "会計",
        "会計"
    ),
    ConversationItem(
        "Tôi trả bằng thẻ",
        "カードで払います",
        "食事",
        "レストラン",
        "会計",
        "カード払い"
    ),
    ConversationItem(
        "Tôi trả bằng tiền mặt",
        "現金で払います",
        "食事",
        "レストラン",
        "会計",
        "現金払い"
    ),
    ConversationItem(
        "Bữa ăn rất ngon",
        "とてもおいしかったです",
        "食事",
        "レストラン",
        "感想",
        "評価"
    ),
    ConversationItem(
        "Tôi rất hài lòng",
        "とても満足しました",
        "食事",
        "レストラン",
        "感想",
        "満足"
    ),
    ConversationItem(
        "Tôi muốn quay lại lần nữa",
        "また来たいです",
        "食事",
        "レストラン",
        "感想",
        "再来店"
    ),

    //
    // ✅ Part16 カフェ
    //
    ConversationItem(
        "Cho tôi một cà phê sữa đá",
        "ベトナムアイスコーヒーをください",
        "食事",
        "カフェ",
        "注文",
        "コーヒー"
    ),
    ConversationItem(
        "Cho tôi một trà đá",
        "アイスティーをください",
        "食事",
        "カフェ",
        "注文",
        "飲み物"
    ),
    ConversationItem(
        "Tôi muốn gọi món này",
        "これを注文したいです",
        "食事",
        "カフェ",
        "注文",
        "注文"
    ),
    ConversationItem(
        "Ít đường thôi",
        "砂糖は少なめでお願いします",
        "食事",
        "カフェ",
        "注文調整",
        "砂糖"
    ),
    ConversationItem(
        "Không đá nhé",
        "氷なしでお願いします",
        "食事",
        "カフェ",
        "注文調整",
        "氷なし"
    ),
    ConversationItem(
        "Cho thêm sữa được không?",
        "ミルクを追加できますか？",
        "食事",
        "カフェ",
        "注文調整",
        "追加注文"
    ),
    ConversationItem(
        "Ở đây có Wi-Fi không?",
        "Wi-Fiはありますか？",
        "食事",
        "カフェ",
        "設備確認",
        "Wi-Fi"
    ),
    ConversationItem(
        "Mật khẩu Wi-Fi là gì?",
        "Wi-Fiのパスワードは何ですか？",
        "食事",
        "カフェ",
        "設備確認",
        "パスワード"
    ),
    ConversationItem(
        "Có ổ cắm điện không?",
        "コンセントはありますか？",
        "食事",
        "カフェ",
        "設備確認",
        "電源"
    ),
    ConversationItem(
        "Tôi ngồi ở đây được không?",
        "ここに座ってもいいですか？",
        "食事",
        "カフェ",
        "席",
        "着席"
    ),
    ConversationItem(
        "Chỗ này còn trống không?",
        "この席は空いていますか？",
        "食事",
        "カフェ",
        "席",
        "空席"
    ),
    ConversationItem(
        "Tôi ngồi cùng được không?",
        "一緒に座ってもいいですか？",
        "食事",
        "カフェ",
        "席",
        "相席"
    ),
    ConversationItem(
        "Tôi muốn làm việc ở đây",
        "ここで仕事をしたいです",
        "食事",
        "カフェ",
        "作業",
        "仕事"
    ),
    ConversationItem(
        "Ở đây yên tĩnh quá",
        "ここは静かですね",
        "食事",
        "カフェ",
        "作業",
        "環境"
    ),
    ConversationItem(
        "Tôi cần sạc máy tính",
        "パソコンを充電したいです",
        "食事",
        "カフェ",
        "作業",
        "充電"
    ),
    ConversationItem(
        "Cà phê rất ngon",
        "コーヒーがおいしいです",
        "食事",
        "カフェ",
        "感想",
        "味"
    ),
    ConversationItem(
        "Không gian ở đây đẹp quá",
        "ここは雰囲気がいいですね",
        "食事",
        "カフェ",
        "感想",
        "雰囲気"
    ),
    ConversationItem(
        "Tôi thích quán này",
        "このカフェが気に入りました",
        "食事",
        "カフェ",
        "感想",
        "好み"
    ),

    //
    // ✅ Part17 ホテル
    //
    ConversationItem(
        "Tôi muốn nhận phòng",
        "チェックインしたいです",
        "住居",
        "ホテル",
        "チェックイン",
        "チェックイン"
    ),
    ConversationItem(
        "Tôi có đặt phòng",
        "予約しています",
        "住居",
        "ホテル",
        "チェックイン",
        "予約"
    ),
    ConversationItem(
        "Đây là hộ chiếu của tôi",
        "こちらがパスポートです",
        "住居",
        "ホテル",
        "チェックイン",
        "本人確認"
    ),
    ConversationItem(
        "Bữa sáng bắt đầu lúc mấy giờ?",
        "朝食は何時からですか？",
        "住居",
        "ホテル",
        "設備確認",
        "朝食"
    ),
    ConversationItem(
        "Hồ bơi ở tầng mấy?",
        "プールは何階ですか？",
        "住居",
        "ホテル",
        "設備確認",
        "施設"
    ),
    ConversationItem(
        "Wi-Fi có miễn phí không?",
        "Wi‑Fiは無料ですか？",
        "住居",
        "ホテル",
        "設備確認",
        "Wi‑Fi"
    ),
    ConversationItem(
        "Cho tôi thêm khăn tắm",
        "タオルを追加してください",
        "住居",
        "ホテル",
        "備品依頼",
        "タオル"
    ),
    ConversationItem(
        "Cho tôi thêm nước uống",
        "飲み水を追加してください",
        "住居",
        "ホテル",
        "備品依頼",
        "水"
    ),
    ConversationItem(
        "Cho tôi thêm gối",
        "枕を追加してください",
        "住居",
        "ホテル",
        "備品依頼",
        "枕"
    ),
    ConversationItem(
        "Điều hòa không hoạt động",
        "エアコンが動きません",
        "住居",
        "ホテル",
        "トラブル",
        "エアコン"
    ),
    ConversationItem(
        "Wi-Fi trong phòng không hoạt động",
        "部屋のWi‑Fiが使えません",
        "住居",
        "ホテル",
        "トラブル",
        "Wi‑Fi"
    ),
    ConversationItem(
        "Tôi không mở được cửa",
        "ドアが開きません",
        "住居",
        "ホテル",
        "トラブル",
        "ドア"
    ),
    ConversationItem(
        "Tôi muốn ở thêm một đêm",
        "もう一泊したいです",
        "住居",
        "ホテル",
        "延泊",
        "延泊"
    ),
    ConversationItem(
        "Còn phòng trống không?",
        "空き部屋はありますか？",
        "住居",
        "ホテル",
        "延泊",
        "空室確認"
    ),
    ConversationItem(
        "Giá một đêm là bao nhiêu?",
        "1泊いくらですか？",
        "住居",
        "ホテル",
        "延泊",
        "料金"
    ),
    ConversationItem(
        "Tôi muốn trả phòng",
        "チェックアウトします",
        "住居",
        "ホテル",
        "チェックアウト",
        "チェックアウト"
    ),
    ConversationItem(
        "Tôi muốn thanh toán",
        "支払いをお願いします",
        "住居",
        "ホテル",
        "チェックアウト",
        "精算"
    ),
    ConversationItem(
        "Cảm ơn vì đã giúp đỡ",
        "お世話になりました",
        "住居",
        "ホテル",
        "チェックアウト",
        "お礼"
    ),

    //
    // ✅ Part18 市場・土産
    //
    ConversationItem(
        "Món này được làm ở đâu?",
        "これはどこで作られましたか？",
        "買い物",
        "市場・土産",
        "商品確認",
        "産地"
    ),
    ConversationItem(
        "Có màu khác không?",
        "別の色はありますか？",
        "買い物",
        "市場・土産",
        "商品確認",
        "色"
    ),
    ConversationItem(
        "Có kích thước lớn hơn không?",
        "もっと大きいサイズはありますか？",
        "買い物",
        "市場・土産",
        "商品確認",
        "サイズ"
    ),
    ConversationItem(
        "Cái này bao nhiêu tiền?",
        "これはいくらですか？",
        "買い物",
        "市場・土産",
        "値段確認",
        "値段"
    ),
    ConversationItem(
        "Cái kia bao nhiêu tiền?",
        "あれはいくらですか？",
        "買い物",
        "市場・土産",
        "値段確認",
        "値段"
    ),
    ConversationItem(
        "Tổng cộng bao nhiêu?",
        "全部でいくらですか？",
        "買い物",
        "市場・土産",
        "値段確認",
        "合計金額"
    ),
    ConversationItem(
        "Giảm giá được không?",
        "少し安くなりませんか？",
        "買い物",
        "市場・土産",
        "値引き",
        "値引き"
    ),
    ConversationItem(
        "Giá này hơi cao",
        "この値段は少し高いです",
        "買い物",
        "市場・土産",
        "値引き",
        "高い"
    ),
    ConversationItem(
        "Có thể rẻ hơn không?",
        "もう少し安くできますか？",
        "買い物",
        "市場・土産",
        "値引き",
        "価格交渉"
    ),
    ConversationItem(
        "Tôi lấy cái này",
        "これをください",
        "買い物",
        "市場・土産",
        "購入",
        "購入"
    ),
    ConversationItem(
        "Cho tôi hai cái",
        "2つください",
        "買い物",
        "市場・土産",
        "購入",
        "数量指定"
    ),
    ConversationItem(
        "Tôi chỉ xem thôi",
        "見ているだけです",
        "買い物",
        "市場・土産",
        "購入",
        "閲覧"
    ),
    ConversationItem(
        "Đây có phải hàng thủ công không?",
        "これは手工芸品ですか？",
        "買い物",
        "市場・土産",
        "お土産",
        "商品確認"
    ),
    ConversationItem(
        "Món quà này dành cho ai?",
        "これは誰向けのお土産ですか？",
        "買い物",
        "市場・土産",
        "お土産",
        "商品確認"
    ),
    ConversationItem(
        "Đây là đặc sản địa phương phải không?",
        "これは地元の名産品ですか？",
        "買い物",
        "市場・土産",
        "お土産",
        "特産品"
    ),
    ConversationItem(
        "Tôi thích món này",
        "これが気に入りました",
        "買い物",
        "市場・土産",
        "感想",
        "好み"
    ),
    ConversationItem(
        "Món này rất đẹp",
        "これはとてもきれいですね",
        "買い物",
        "市場・土産",
        "感想",
        "感想"
    ),
    ConversationItem(
        "Tôi muốn mua làm quà",
        "お土産として買いたいです",
        "買い物",
        "市場・土産",
        "感想",
        "購入意欲"
    ),

    //
    // ✅ Part19 スーパー・コンビニ
    //
    ConversationItem(
        "Sữa ở đâu?",
        "牛乳はどこですか？",
        "買い物",
        "スーパー・コンビニ",
        "商品確認",
        "売り場"
    ),
    ConversationItem(
        "Nước uống ở đâu?",
        "飲み物はどこですか？",
        "買い物",
        "スーパー・コンビニ",
        "商品確認",
        "売り場"
    ),
    ConversationItem(
        "Có bán pin không?",
        "電池は売っていますか？",
        "買い物",
        "スーパー・コンビニ",
        "商品確認",
        "商品確認"
    ),
    ConversationItem(
        "Trái cây ở đâu?",
        "果物はどこですか？",
        "買い物",
        "スーパー・コンビニ",
        "場所確認",
        "売り場"
    ),
    ConversationItem(
        "Bánh mì ở đâu?",
        "パン売り場はどこですか？",
        "買い物",
        "スーパー・コンビニ",
        "場所確認",
        "売り場"
    ),
    ConversationItem(
        "Quầy tính tiền ở đâu?",
        "レジはどこですか？",
        "買い物",
        "スーパー・コンビニ",
        "場所確認",
        "レジ"
    ),
    ConversationItem(
        "Tôi lấy cái này",
        "これをください",
        "買い物",
        "スーパー・コンビニ",
        "購入",
        "購入"
    ),
    ConversationItem(
        "Cho tôi hai chai nước",
        "水を2本ください",
        "買い物",
        "スーパー・コンビニ",
        "購入",
        "数量指定"
    ),
    ConversationItem(
        "Tôi muốn mua cái này",
        "これを買いたいです",
        "買い物",
        "スーパー・コンビニ",
        "購入",
        "購入"
    ),
    ConversationItem(
        "Có thanh toán bằng thẻ không?",
        "カードで払えますか？",
        "買い物",
        "スーパー・コンビニ",
        "支払い",
        "カード"
    ),
    ConversationItem(
        "Có hỗ trợ QR không?",
        "QR決済できますか？",
        "買い物",
        "スーパー・コンビニ",
        "支払い",
        "QR決済"
    ),
    ConversationItem(
        "Tôi trả bằng tiền mặt",
        "現金で払います",
        "買い物",
        "スーパー・コンビニ",
        "支払い",
        "現金"
    ),
    ConversationItem(
        "Cho tôi một túi nhé",
        "袋をください",
        "買い物",
        "スーパー・コンビニ",
        "レジ",
        "袋"
    ),
    ConversationItem(
        "Không cần túi",
        "袋はいりません",
        "買い物",
        "スーパー・コンビニ",
        "レジ",
        "袋不要"
    ),
    ConversationItem(
        "Cho tôi hóa đơn",
        "レシートをください",
        "買い物",
        "スーパー・コンビニ",
        "レジ",
        "レシート"
    ),
    ConversationItem(
        "Hâm nóng giúp tôi được không?",
        "温めてもらえますか？",
        "買い物",
        "スーパー・コンビニ",
        "サービス",
        "温め"
    ),
    ConversationItem(
        "Có thể đổi tiền lẻ không?",
        "小銭に両替できますか？",
        "買い物",
        "スーパー・コンビニ",
        "サービス",
        "両替"
    ),
    ConversationItem(
        "Có thể sạc điện thoại ở đây không?",
        "ここでスマホを充電できますか？",
        "買い物",
        "スーパー・コンビニ",
        "サービス",
        "充電"
    ),

    //
    // ✅ Part20 薬局
    //
    ConversationItem(
        "Tôi bị đau đầu",
        "頭が痛いです",
        "医療",
        "薬局",
        "症状説明",
        "頭痛"
    ),
    ConversationItem(
        "Tôi bị đau bụng",
        "お腹が痛いです",
        "医療",
        "薬局",
        "症状説明",
        "腹痛"
    ),
    ConversationItem(
        "Tôi bị sốt",
        "熱があります",
        "医療",
        "薬局",
        "症状説明",
        "発熱"
    ),
    ConversationItem(
        "Tôi bị cảm",
        "風邪をひきました",
        "医療",
        "薬局",
        "体調説明",
        "風邪"
    ),
    ConversationItem(
        "Tôi bị ho",
        "咳が出ます",
        "医療",
        "薬局",
        "体調説明",
        "咳"
    ),
    ConversationItem(
        "Tôi bị đau họng",
        "喉が痛いです",
        "医療",
        "薬局",
        "体調説明",
        "喉の痛み"
    ),
    ConversationItem(
        "Có thuốc cảm không?",
        "風邪薬はありますか？",
        "医療",
        "薬局",
        "薬を探す",
        "風邪薬"
    ),
    ConversationItem(
        "Có thuốc giảm đau không?",
        "痛み止めはありますか？",
        "医療",
        "薬局",
        "薬を探す",
        "痛み止め"
    ),
    ConversationItem(
        "Có thuốc đau bụng không?",
        "胃腸薬はありますか？",
        "医療",
        "薬局",
        "薬を探す",
        "胃腸薬"
    ),
    ConversationItem(
        "Uống thuốc này như thế nào?",
        "この薬はどう飲みますか？",
        "医療",
        "薬局",
        "使い方確認",
        "服用方法"
    ),
    ConversationItem(
        "Ngày uống mấy lần?",
        "1日に何回飲みますか？",
        "医療",
        "薬局",
        "使い方確認",
        "回数"
    ),
    ConversationItem(
        "Uống trước hay sau bữa ăn?",
        "食前ですか？食後ですか？",
        "医療",
        "薬局",
        "使い方確認",
        "服用タイミング"
    ),
    ConversationItem(
        "Tôi bị dị ứng thuốc này",
        "この薬にアレルギーがあります",
        "医療",
        "薬局",
        "アレルギー",
        "薬"
    ),
    ConversationItem(
        "Thuốc này có tác dụng phụ không?",
        "この薬に副作用はありますか？",
        "医療",
        "薬局",
        "アレルギー",
        "副作用"
    ),
    ConversationItem(
        "Thuốc này có gây buồn ngủ không?",
        "この薬は眠くなりますか？",
        "医療",
        "薬局",
        "アレルギー",
        "眠気"
    ),
    ConversationItem(
        "Tôi lấy thuốc này",
        "この薬をください",
        "医療",
        "薬局",
        "支払い",
        "購入"
    ),
    ConversationItem(
        "Hết bao nhiêu tiền?",
        "いくらですか？",
        "医療",
        "薬局",
        "支払い",
        "金額確認"
    ),
    ConversationItem(
        "Tôi trả bằng thẻ",
        "カードで払います",
        "医療",
        "薬局",
        "支払い",
        "支払い"
    ),

    //
    // ✅ Part21 地下鉄・バス
    //
    ConversationItem(
        "Ga metro ở đâu?",
        "地下鉄の駅はどこですか？",
        "交通",
        "地下鉄・バス",
        "駅確認",
        "駅の場所"
    ),
    ConversationItem(
        "Lối vào ga ở đâu?",
        "駅の入口はどこですか？",
        "交通",
        "地下鉄・バス",
        "駅確認",
        "入口確認"
    ),
    ConversationItem(
        "Ga này đi đâu?",
        "この駅からどこへ行けますか？",
        "交通",
        "地下鉄・バス",
        "駅確認",
        "行き先確認"
    ),
    ConversationItem(
        "Tôi muốn mua vé",
        "切符を買いたいです",
        "交通",
        "地下鉄・バス",
        "切符",
        "切符購入"
    ),
    ConversationItem(
        "Mua vé ở đâu?",
        "切符はどこで買えますか？",
        "交通",
        "地下鉄・バス",
        "切符",
        "購入場所"
    ),
    ConversationItem(
        "Tôi có thể thanh toán bằng thẻ không?",
        "カードで支払えますか？",
        "交通",
        "地下鉄・バス",
        "切符",
        "支払い方法"
    ),
    ConversationItem(
        "Tàu này đi đến Quận 1 không?",
        "この電車は1区へ行きますか？",
        "交通",
        "地下鉄・バス",
        "乗車確認",
        "行き先"
    ),
    ConversationItem(
        "Tôi nên lên tàu nào?",
        "どの電車に乗ればいいですか？",
        "交通",
        "地下鉄・バス",
        "乗車確認",
        "路線確認"
    ),
    ConversationItem(
        "Tôi nên đổi tàu ở đâu?",
        "どこで乗り換えればいいですか？",
        "交通",
        "地下鉄・バス",
        "乗車確認",
        "乗換案内"
    ),
    ConversationItem(
        "Tàu hôm nay đông quá",
        "今日は電車が混んでいますね",
        "交通",
        "地下鉄・バス",
        "混雑",
        "混雑"
    ),
    ConversationItem(
        "Xe buýt rất đông",
        "バスがとても混んでいます",
        "交通",
        "地下鉄・バス",
        "混雑",
        "混雑"
    ),
    ConversationItem(
        "Có ghế trống không?",
        "空席はありますか？",
        "交通",
        "地下鉄・バス",
        "混雑",
        "空席"
    ),
    ConversationItem(
        "Tôi xuống ở ga nào?",
        "私はどの駅で降りればいいですか？",
        "交通",
        "地下鉄・バス",
        "降車確認",
        "降車駅"
    ),
    ConversationItem(
        "Còn mấy ga nữa?",
        "あと何駅ですか？",
        "交通",
        "地下鉄・バス",
        "降車確認",
        "残り駅数"
    ),
    ConversationItem(
        "Ga tiếp theo là ga nào?",
        "次の駅はどこですか？",
        "交通",
        "地下鉄・バス",
        "降車確認",
        "次駅確認"
    ),
    ConversationItem(
        "Xe buýt này đi đến đâu?",
        "このバスはどこへ行きますか？",
        "交通",
        "地下鉄・バス",
        "路線確認",
        "行き先"
    ),
    ConversationItem(
        "Tôi nên xuống ở đâu?",
        "どこで降りればいいですか？",
        "交通",
        "地下鉄・バス",
        "路線確認",
        "降車場所"
    ),
    ConversationItem(
        "Trạm xe buýt ở đâu?",
        "バス停はどこですか？",
        "交通",
        "地下鉄・バス",
        "路線確認",
        "バス停"
    ),

    //
    // ✅ Part22 Grab・タクシー
    //
    ConversationItem(
        "Tôi muốn đi Quận 1",
        "1区へ行きたいです",
        "交通",
        "Grab・タクシー",
        "目的地",
        "目的地"
    ),
    ConversationItem(
        "Đến đường Nguyễn Huệ nhé",
        "グエンフエ通りまでお願いします",
        "交通",
        "Grab・タクシー",
        "目的地",
        "目的地"
    ),
    ConversationItem(
        "Đến sân bay nhé",
        "空港までお願いします",
        "交通",
        "Grab・タクシー",
        "目的地",
        "目的地"
    ),
    ConversationItem(
        "Tôi đặt Grab rồi",
        "Grabを呼びました",
        "交通",
        "Grab・タクシー",
        "配車",
        "配車"
    ),
    ConversationItem(
        "Tài xế sắp đến",
        "運転手がもうすぐ来ます",
        "交通",
        "Grab・タクシー",
        "配車",
        "到着待ち"
    ),
    ConversationItem(
        "Tôi đang chờ Grab",
        "Grabを待っています",
        "交通",
        "Grab・タクシー",
        "配車",
        "待機"
    ),
    ConversationItem(
        "Bạn đang ở đâu?",
        "今どこにいますか？",
        "交通",
        "Grab・タクシー",
        "待ち合わせ",
        "現在地確認"
    ),
    ConversationItem(
        "Tôi đang đứng trước khách sạn",
        "ホテルの前にいます",
        "交通",
        "Grab・タクシー",
        "待ち合わせ",
        "現在地"
    ),
    ConversationItem(
        "Tôi mặc áo màu xanh",
        "青い服を着ています",
        "交通",
        "Grab・タクシー",
        "待ち合わせ",
        "目印"
    ),
    ConversationItem(
        "Dừng ở đây nhé",
        "ここで止めてください",
        "交通",
        "Grab・タクシー",
        "降車",
        "降車"
    ),
    ConversationItem(
        "Tôi xuống ở đây",
        "ここで降ります",
        "交通",
        "Grab・タクシー",
        "降車",
        "降車"
    ),
    ConversationItem(
        "Đi chậm một chút nhé",
        "少しゆっくりお願いします",
        "交通",
        "Grab・タクシー",
        "降車",
        "運転"
    ),
    ConversationItem(
        "Hôm nay kẹt xe quá",
        "今日は渋滞がひどいですね",
        "交通",
        "Grab・タクシー",
        "移動中",
        "渋滞"
    ),
    ConversationItem(
        "Còn bao lâu nữa?",
        "あとどれくらいですか？",
        "交通",
        "Grab・タクシー",
        "移動中",
        "所要時間"
    ),
    ConversationItem(
        "Chúng ta sắp đến chưa?",
        "もうすぐ着きますか？",
        "交通",
        "Grab・タクシー",
        "移動中",
        "到着確認"
    ),
    ConversationItem(
        "Tôi trả bằng tiền mặt",
        "現金で払います",
        "交通",
        "Grab・タクシー",
        "支払い",
        "現金"
    ),
    ConversationItem(
        "Tôi trả qua ứng dụng",
        "アプリで支払います",
        "交通",
        "Grab・タクシー",
        "支払い",
        "アプリ決済"
    ),
    ConversationItem(
        "Cảm ơn tài xế",
        "ありがとうございました",
        "交通",
        "Grab・タクシー",
        "支払い",
        "お礼"
    ),
    ConversationItem(
        "Xe này có điều hòa không?",
        "この車はエアコンがありますか？",
        "交通",
        "Grab・タクシー",
        "車内",
        "設備"
    ),
    ConversationItem(
        "Bật điều hòa giúp tôi nhé",
        "エアコンをつけてもらえますか？",
        "交通",
        "Grab・タクシー",
        "車内",
        "依頼"
    ),
    ConversationItem(
        "Trong xe hơi lạnh",
        "車内が少し寒いです",
        "交通",
        "Grab・タクシー",
        "車内",
        "感想"
    ),
    ConversationItem(
        "Tôi muốn đi bằng xe máy",
        "バイクタクシーで行きたいです",
        "交通",
        "Grab・タクシー",
        "GrabBike",
        "利用"
    ),
    ConversationItem(
        "Xe máy nhanh hơn phải không?",
        "バイクの方が早いですよね？",
        "交通",
        "Grab・タクシー",
        "GrabBike",
        "確認"
    ),
    ConversationItem(
        "Tôi đội mũ bảo hiểm rồi",
        "ヘルメットを着けました",
        "交通",
        "Grab・タクシー",
        "GrabBike",
        "乗車準備"
    ),

    //
    // ✅ Part23 道案内・迷子
    //
    ConversationItem(
        "Tôi bị lạc đường",
        "道に迷いました",
        "交通",
        "道案内・迷子",
        "迷子",
        "迷子"
    ),
    ConversationItem(
        "Tôi không biết đường đi",
        "行き方が分かりません",
        "交通",
        "道案内・迷子",
        "迷子",
        "迷子"
    ),
    ConversationItem(
        "Bạn giúp tôi được không?",
        "手伝ってもらえますか？",
        "交通",
        "道案内・迷子",
        "迷子",
        "助けを求める"
    ),
    ConversationItem(
        "Đây là đâu?",
        "ここはどこですか？",
        "交通",
        "道案内・迷子",
        "現在地確認",
        "現在地"
    ),
    ConversationItem(
        "Tôi đang ở đâu?",
        "私は今どこにいますか？",
        "交通",
        "道案内・迷子",
        "現在地確認",
        "現在地"
    ),
    ConversationItem(
        "Đây có phải Quận 1 không?",
        "ここは1区ですか？",
        "交通",
        "道案内・迷子",
        "現在地確認",
        "場所確認"
    ),
    ConversationItem(
        "Ga metro ở đâu?",
        "地下鉄の駅はどこですか？",
        "交通",
        "道案内・迷子",
        "場所確認",
        "駅"
    ),
    ConversationItem(
        "Bến xe buýt ở đâu?",
        "バス停はどこですか？",
        "交通",
        "道案内・迷子",
        "場所確認",
        "バス停"
    ),
    ConversationItem(
        "Nhà vệ sinh ở đâu?",
        "トイレはどこですか？",
        "交通",
        "道案内・迷子",
        "場所確認",
        "トイレ"
    ),
    ConversationItem(
        "Tôi đi đến đây bằng cách nào?",
        "ここへはどうやって行きますか？",
        "交通",
        "道案内・迷子",
        "行き方確認",
        "行き方"
    ),
    ConversationItem(
        "Tôi nên đi hướng nào?",
        "どちらへ行けばいいですか？",
        "交通",
        "道案内・迷子",
        "行き方確認",
        "方向"
    ),
    ConversationItem(
        "Có xa không?",
        "遠いですか？",
        "交通",
        "道案内・迷子",
        "行き方確認",
        "距離"
    ),
    ConversationItem(
        "Đi thẳng phải không?",
        "まっすぐ行けばいいですか？",
        "交通",
        "道案内・迷子",
        "方向確認",
        "直進"
    ),
    ConversationItem(
        "Rẽ trái ở đâu?",
        "どこで左に曲がりますか？",
        "交通",
        "道案内・迷子",
        "方向確認",
        "左折"
    ),
    ConversationItem(
        "Rẽ phải ở đâu?",
        "どこで右に曲がりますか？",
        "交通",
        "道案内・迷子",
        "方向確認",
        "右折"
    ),
    ConversationItem(
        "Mất bao lâu để đến đó?",
        "そこまでどのくらいかかりますか？",
        "交通",
        "道案内・迷子",
        "所要時間",
        "時間"
    ),
    ConversationItem(
        "Đi bộ mất bao lâu?",
        "歩いてどのくらいですか？",
        "交通",
        "道案内・迷子",
        "所要時間",
        "徒歩"
    ),
    ConversationItem(
        "Đi bằng xe máy mất bao lâu?",
        "バイクだとどのくらいですか？",
        "交通",
        "道案内・迷子",
        "所要時間",
        "移動時間"
    ),
    ConversationItem(
        "Tôi muốn đến chợ Bến Thành",
        "ベンタイン市場へ行きたいです",
        "交通",
        "道案内・迷子",
        "目的地",
        "市場"
    ),
    ConversationItem(
        "Tôi muốn đến phố đi bộ Nguyễn Huệ",
        "グエンフエ通りへ行きたいです",
        "交通",
        "道案内・迷子",
        "目的地",
        "観光地"
    ),
    ConversationItem(
        "Tôi muốn đến Bưu điện Trung tâm",
        "中央郵便局へ行きたいです",
        "交通",
        "道案内・迷子",
        "目的地",
        "観光地"
    ),

    //
    // ✅ Part24 遺失・盗難
    //
    ConversationItem(
        "Tôi làm mất ví",
        "財布をなくしました",
        "住居",
        "遺失・盗難",
        "財布",
        "財布紛失"
    ),
    ConversationItem(
        "Tôi không tìm thấy ví",
        "財布が見つかりません",
        "住居",
        "遺失・盗難",
        "財布",
        "財布紛失"
    ),
    ConversationItem(
        "Tôi nghĩ tôi làm rơi ví",
        "財布を落としたと思います",
        "住居",
        "遺失・盗難",
        "財布",
        "落とし物"
    ),
    ConversationItem(
        "Tôi làm mất điện thoại",
        "スマホをなくしました",
        "住居",
        "遺失・盗難",
        "スマホ",
        "スマホ紛失"
    ),
    ConversationItem(
        "Tôi không tìm thấy điện thoại",
        "スマホが見つかりません",
        "住居",
        "遺失・盗難",
        "スマホ",
        "スマホ紛失"
    ),
    ConversationItem(
        "Điện thoại của tôi hết pin",
        "スマホの電池が切れています",
        "住居",
        "遺失・盗難",
        "スマホ",
        "連絡不能"
    ),
    ConversationItem(
        "Bạn có thấy ví của tôi không?",
        "私の財布を見ませんでしたか？",
        "住居",
        "遺失・盗難",
        "捜索",
        "財布捜索"
    ),
    ConversationItem(
        "Bạn có thấy điện thoại của tôi không?",
        "私のスマホを見ませんでしたか？",
        "住居",
        "遺失・盗難",
        "捜索",
        "スマホ捜索"
    ),
    ConversationItem(
        "Tôi đánh rơi đồ ở đâu đó",
        "どこかで物を落としました",
        "住居",
        "遺失・盗難",
        "捜索",
        "落とし物"
    ),
    ConversationItem(
        "Tôi để quên đồ ở đây",
        "ここに忘れ物をしました",
        "住居",
        "遺失・盗難",
        "忘れ物",
        "忘れ物"
    ),
    ConversationItem(
        "Tôi để quên túi ở nhà hàng",
        "レストランにカバンを忘れました",
        "住居",
        "遺失・盗難",
        "忘れ物",
        "忘れ物"
    ),
    ConversationItem(
        "Tôi để quên đồ trong Grab",
        "Grabに忘れ物をしました",
        "住居",
        "遺失・盗難",
        "忘れ物",
        "忘れ物"
    ),
    ConversationItem(
        "Tôi bị mất cắp",
        "盗難に遭いました",
        "住居",
        "遺失・盗難",
        "被害報告",
        "盗難"
    ),
    ConversationItem(
        "Ai đó lấy mất ví của tôi",
        "誰かに財布を盗まれました",
        "住居",
        "遺失・盗難",
        "被害報告",
        "財布盗難"
    ),
    ConversationItem(
        "Điện thoại của tôi bị lấy mất",
        "スマホを盗まれました",
        "住居",
        "遺失・盗難",
        "被害報告",
        "スマホ盗難"
    ),
    ConversationItem(
        "Tôi muốn báo cảnh sát",
        "警察に届けたいです",
        "住居",
        "遺失・盗難",
        "警察",
        "警察"
    ),
    ConversationItem(
        "Đồn cảnh sát ở đâu?",
        "警察署はどこですか？",
        "住居",
        "遺失・盗難",
        "警察",
        "警察署"
    ),
    ConversationItem(
        "Tôi cần giấy xác nhận mất đồ",
        "紛失証明が必要です",
        "住居",
        "遺失・盗難",
        "警察",
        "証明書"
    ),
    ConversationItem(
        "Tôi cần khóa thẻ tín dụng",
        "クレジットカードを停止したいです",
        "住居",
        "遺失・盗難",
        "カード",
        "利用停止"
    ),
    ConversationItem(
        "Thẻ của tôi đã bị mất",
        "カードをなくしました",
        "住居",
        "遺失・盗難",
        "カード",
        "カード紛失"
    ),
    ConversationItem(
        "Có giao dịch lạ trong tài khoản",
        "口座に不審な取引があります",
        "住居",
        "遺失・盗難",
        "カード",
        "不正利用"
    ),
    ConversationItem(
        "May quá, tôi tìm thấy rồi",
        "よかった、見つかりました",
        "住居",
        "遺失・盗難",
        "発見",
        "発見"
    ),
    ConversationItem(
        "Tôi tìm thấy ví rồi",
        "財布が見つかりました",
        "住居",
        "遺失・盗難",
        "発見",
        "財布発見"
    ),
    ConversationItem(
        "Tôi tìm thấy điện thoại rồi",
        "スマホが見つかりました",
        "住居",
        "遺失・盗難",
        "発見",
        "スマホ発見"
    ),

    //
    // ✅ Part25 災害・緊急
    //
    ConversationItem(
        "Mưa lớn quá",
        "すごい雨ですね",
        "住居",
        "災害・緊急",
        "大雨",
        "大雨"
    ),
    ConversationItem(
        "Trời đang mưa rất to",
        "雨がとても強く降っています",
        "住居",
        "災害・緊急",
        "大雨",
        "豪雨"
    ),
    ConversationItem(
        "Tôi không thể ra ngoài vì mưa",
        "雨で外に出られません",
        "住居",
        "災害・緊急",
        "大雨",
        "外出不可"
    ),
    ConversationItem(
        "Đường bị ngập rồi",
        "道路が冠水しています",
        "住居",
        "災害・緊急",
        "冠水",
        "冠水"
    ),
    ConversationItem(
        "Nước ngập quá cao",
        "水位がとても高いです",
        "住居",
        "災害・緊急",
        "冠水",
        "浸水"
    ),
    ConversationItem(
        "Xe không đi được",
        "車が通れません",
        "住居",
        "災害・緊急",
        "冠水",
        "交通障害"
    ),
    ConversationItem(
        "Có cháy!",
        "火事です！",
        "住居",
        "災害・緊急",
        "火災",
        "火災"
    ),
    ConversationItem(
        "Tôi thấy khói",
        "煙が見えます",
        "住居",
        "災害・緊急",
        "火災",
        "煙"
    ),
    ConversationItem(
        "Gọi cứu hỏa đi!",
        "消防に連絡してください！",
        "住居",
        "災害・緊急",
        "火災",
        "通報"
    ),
    ConversationItem(
        "Tôi cần xe cứu thương",
        "救急車が必要です",
        "住居",
        "災害・緊急",
        "救急",
        "救急車"
    ),
    ConversationItem(
        "Có người bị thương",
        "けが人がいます",
        "住居",
        "災害・緊急",
        "救急",
        "けが人"
    ),
    ConversationItem(
        "Tôi cần giúp đỡ ngay",
        "今すぐ助けが必要です",
        "住居",
        "災害・緊急",
        "救急",
        "緊急支援"
    ),
    ConversationItem(
        "Hãy chạy ra ngoài",
        "外へ逃げてください",
        "住居",
        "災害・緊急",
        "避難",
        "避難"
    ),
    ConversationItem(
        "Chúng ta cần sơ tán",
        "避難する必要があります",
        "住居",
        "災害・緊急",
        "避難",
        "避難指示"
    ),
    ConversationItem(
        "Lối thoát hiểm ở đâu?",
        "非常口はどこですか？",
        "住居",
        "災害・緊急",
        "避難",
        "避難経路"
    ),
    ConversationItem(
        "Giúp tôi với!",
        "助けてください！",
        "住居",
        "災害・緊急",
        "救助要請",
        "助けを呼ぶ"
    ),
    ConversationItem(
        "Có ai ở đây không?",
        "誰かいますか？",
        "住居",
        "災害・緊急",
        "救助要請",
        "呼びかけ"
    ),
    ConversationItem(
        "Làm ơn giúp tôi",
        "どうか助けてください",
        "住居",
        "災害・緊急",
        "救助要請",
        "救助依頼"
    ),
    ConversationItem(
        "Bình tĩnh nhé",
        "落ち着いてください",
        "住居",
        "災害・緊急",
        "安全確認",
        "落ち着く"
    ),
    ConversationItem(
        "Bạn có sao không?",
        "大丈夫ですか？",
        "住居",
        "災害・緊急",
        "安全確認",
        "安否確認"
    ),
    ConversationItem(
        "Mọi người đều an toàn",
        "みんな無事です",
        "住居",
        "災害・緊急",
        "安全確認",
        "無事確認"
    ),

    //
    // ✅ Part26 家・家電
    //
    ConversationItem(
        "Điều hòa không hoạt động",
        "エアコンが動きません",
        "住居",
        "家・家電",
        "エアコン",
        "故障"
    ),
    ConversationItem(
        "Điều hòa không lạnh",
        "エアコンが冷えません",
        "住居",
        "家・家電",
        "エアコン",
        "冷房不良"
    ),
    ConversationItem(
        "Tôi muốn bật điều hòa",
        "エアコンをつけたいです",
        "住居",
        "家・家電",
        "エアコン",
        "操作"
    ),
    ConversationItem(
        "Tủ lạnh không hoạt động",
        "冷蔵庫が動きません",
        "住居",
        "家・家電",
        "冷蔵庫",
        "故障"
    ),
    ConversationItem(
        "Tủ lạnh không lạnh",
        "冷蔵庫が冷えません",
        "住居",
        "家・家電",
        "冷蔵庫",
        "冷却不良"
    ),
    ConversationItem(
        "Có gì trong tủ lạnh?",
        "冷蔵庫に何がありますか？",
        "住居",
        "家・家電",
        "冷蔵庫",
        "確認"
    ),
    ConversationItem(
        "Máy giặt không hoạt động",
        "洗濯機が動きません",
        "住居",
        "家・家電",
        "洗濯機",
        "故障"
    ),
    ConversationItem(
        "Tôi đang giặt quần áo",
        "洗濯をしています",
        "住居",
        "家・家電",
        "洗濯機",
        "洗濯"
    ),
    ConversationItem(
        "Máy giặt đã xong",
        "洗濯が終わりました",
        "住居",
        "家・家電",
        "洗濯機",
        "完了"
    ),
    ConversationItem(
        "Điện bị mất rồi",
        "停電しました",
        "住居",
        "家・家電",
        "電気",
        "停電"
    ),
    ConversationItem(
        "Đèn không sáng",
        "電気がつきません",
        "住居",
        "家・家電",
        "電気",
        "照明"
    ),
    ConversationItem(
        "Ổ cắm này có điện không?",
        "このコンセントは使えますか？",
        "住居",
        "家・家電",
        "電気",
        "コンセント"
    ),
    ConversationItem(
        "Không có nước",
        "水が出ません",
        "住居",
        "家・家電",
        "水回り",
        "断水"
    ),
    ConversationItem(
        "Nước nóng không ra",
        "お湯が出ません",
        "住居",
        "家・家電",
        "水回り",
        "給湯"
    ),
    ConversationItem(
        "Vòi nước bị rò rỉ",
        "蛇口から水が漏れています",
        "住居",
        "家・家電",
        "水回り",
        "水漏れ"
    ),
    ConversationItem(
        "Tôi đang nấu ăn",
        "料理をしています",
        "住居",
        "家・家電",
        "料理",
        "料理"
    ),
    ConversationItem(
        "Bữa tối đã sẵn sàng",
        "夕食の準備ができました",
        "住居",
        "家・家電",
        "料理",
        "食事準備"
    ),
    ConversationItem(
        "Tôi cần mua nguyên liệu",
        "食材を買う必要があります",
        "住居",
        "家・家電",
        "料理",
        "買い出し"
    ),
    ConversationItem(
        "Tôi đang dọn dẹp",
        "掃除をしています",
        "住居",
        "家・家電",
        "掃除",
        "掃除"
    ),
    ConversationItem(
        "Phòng này cần được dọn",
        "この部屋は掃除が必要です",
        "住居",
        "家・家電",
        "掃除",
        "掃除必要"
    ),
    ConversationItem(
        "Tôi vừa dọn phòng xong",
        "部屋の掃除が終わりました",
        "住居",
        "家・家電",
        "掃除",
        "掃除完了"
    ),
    ConversationItem(
        "Tôi đang rửa bát",
        "食器を洗っています",
        "住居",
        "家・家電",
        "片付け",
        "皿洗い"
    ),
    ConversationItem(
        "Tôi đang gấp quần áo",
        "洗濯物をたたんでいます",
        "住居",
        "家・家電",
        "片付け",
        "洗濯物"
    ),
    ConversationItem(
        "Tôi đang sắp xếp đồ đạc",
        "荷物を整理しています",
        "住居",
        "家・家電",
        "片付け",
        "整理整頓"
    ),
    ConversationItem(
        "Tôi quên chìa khóa ở nhà",
        "家に鍵を忘れました",
        "住居",
        "家・家電",
        "トラブル",
        "鍵"
    ),
    ConversationItem(
        "Tôi không mở được cửa",
        "ドアが開きません",
        "住居",
        "家・家電",
        "トラブル",
        "ドア"
    ),
    ConversationItem(
        "Khóa cửa bị hỏng",
        "鍵が壊れています",
        "住居",
        "家・家電",
        "トラブル",
        "故障"
    ),

    //
    // ✅ Part27 昼休み雑談
    //
    ConversationItem(
        "Bạn ăn trưa chưa?",
        "もう昼ご飯を食べましたか？",
        "雑談",
        "昼休み",
        "昼食",
        "昼食確認"
    ),
    ConversationItem(
        "Hôm nay bạn ăn gì?",
        "今日は何を食べましたか？",
        "雑談",
        "昼休み",
        "昼食",
        "昼食内容"
    ),
    ConversationItem(
        "Bạn thường ăn trưa ở đâu?",
        "普段どこで昼ご飯を食べますか？",
        "雑談",
        "昼休み",
        "昼食",
        "昼食場所"
    ),
    ConversationItem(
        "Chúng ta đi ăn trưa nhé",
        "昼ご飯を食べに行きましょう",
        "雑談",
        "昼休み",
        "食事の誘い",
        "誘い"
    ),
    ConversationItem(
        "Bạn muốn ăn gì?",
        "何が食べたいですか？",
        "雑談",
        "昼休み",
        "食事の誘い",
        "希望確認"
    ),
    ConversationItem(
        "Gần đây có quán nào ngon không?",
        "この近くにおいしい店はありますか？",
        "雑談",
        "昼休み",
        "食事の誘い",
        "店探し"
    ),
    ConversationItem(
        "Bạn có quán ăn nào giới thiệu không?",
        "おすすめのお店はありますか？",
        "雑談",
        "昼休み",
        "おすすめ",
        "おすすめ確認"
    ),
    ConversationItem(
        "Quán này nổi tiếng phải không?",
        "このお店は有名ですよね？",
        "雑談",
        "昼休み",
        "おすすめ",
        "評判確認"
    ),
    ConversationItem(
        "Món nào ngon nhất ở đây?",
        "ここで一番おすすめの料理は何ですか？",
        "雑談",
        "昼休み",
        "おすすめ",
        "おすすめ料理"
    ),
    ConversationItem(
        "Buổi chiều bạn có bận không?",
        "午後は忙しいですか？",
        "雑談",
        "昼休み",
        "午後の予定",
        "予定確認"
    ),
    ConversationItem(
        "Chiều nay bạn làm gì?",
        "今日の午後は何をしますか？",
        "雑談",
        "昼休み",
        "午後の予定",
        "予定確認"
    ),
    ConversationItem(
        "Chiều nay tôi có cuộc họp",
        "今日の午後は会議があります",
        "雑談",
        "昼休み",
        "午後の予定",
        "予定共有"
    ),
    ConversationItem(
        "Tôi muốn nghỉ một chút",
        "少し休みたいです",
        "雑談",
        "昼休み",
        "休憩",
        "休憩"
    ),
    ConversationItem(
        "Chúng ta uống cà phê nhé",
        "コーヒーを飲みに行きましょう",
        "雑談",
        "昼休み",
        "休憩",
        "カフェ"
    ),
    ConversationItem(
        "Tôi đi dạo một chút",
        "少し散歩してきます",
        "雑談",
        "昼休み",
        "休憩",
        "散歩"
    ),
    ConversationItem(
        "Cuối tuần của bạn thế nào?",
        "週末はどうでしたか？",
        "雑談",
        "昼休み",
        "雑談",
        "週末"
    ),
    ConversationItem(
        "Hôm nay thời tiết nóng quá",
        "今日はとても暑いですね",
        "雑談",
        "昼休み",
        "雑談",
        "天気"
    ),
    ConversationItem(
        "Bạn quê ở đâu?",
        "出身はどこですか？",
        "雑談",
        "昼休み",
        "雑談",
        "出身地"
    ),

    //
    // ✅ Part28 通勤
    //
    ConversationItem(
        "Hôm nay đường đông quá",
        "今日は道路がとても混んでいます",
        "雑談",
        "通勤",
        "渋滞",
        "渋滞"
    ),
    ConversationItem(
        "Tôi bị kẹt xe",
        "渋滞にはまっています",
        "雑談",
        "通勤",
        "渋滞",
        "渋滞"
    ),
    ConversationItem(
        "Buổi sáng thường kẹt xe",
        "朝はよく渋滞します",
        "雑談",
        "通勤",
        "渋滞",
        "朝の渋滞"
    ),
    ConversationItem(
        "Tôi đi làm bằng xe máy",
        "バイクで通勤しています",
        "雑談",
        "通勤",
        "交通手段",
        "バイク通勤"
    ),
    ConversationItem(
        "Tôi đi làm bằng xe buýt",
        "バスで通勤しています",
        "雑談",
        "通勤",
        "交通手段",
        "バス通勤"
    ),
    ConversationItem(
        "Tôi đi làm bằng metro",
        "地下鉄で通勤しています",
        "雑談",
        "通勤",
        "交通手段",
        "地下鉄通勤"
    ),
    ConversationItem(
        "Bạn mất bao lâu để đi làm?",
        "通勤にどれくらいかかりますか？",
        "雑談",
        "通勤",
        "通勤時間",
        "通勤時間"
    ),
    ConversationItem(
        "Tôi mất khoảng 30 phút",
        "だいたい30分かかります",
        "雑談",
        "通勤",
        "通勤時間",
        "所要時間"
    ),
    ConversationItem(
        "Hôm nay tôi đến sớm",
        "今日は早く到着しました",
        "雑談",
        "通勤",
        "通勤時間",
        "早着"
    ),
    ConversationItem(
        "Tôi phải dậy sớm",
        "早起きしなければなりません",
        "雑談",
        "通勤",
        "朝の習慣",
        "早起き"
    ),
    ConversationItem(
        "Tôi thường ra khỏi nhà lúc 7 giờ",
        "普段7時に家を出ます",
        "雑談",
        "通勤",
        "朝の習慣",
        "出発時間"
    ),
    ConversationItem(
        "Tôi ăn sáng trước khi đi làm",
        "出勤前に朝食を食べます",
        "雑談",
        "通勤",
        "朝の習慣",
        "朝食"
    ),
    ConversationItem(
        "Hôm nay trời mưa khi đi làm",
        "今日は通勤中に雨が降りました",
        "雑談",
        "通勤",
        "天気",
        "雨"
    ),
    ConversationItem(
        "Tôi mang áo mưa theo",
        "雨具を持ってきました",
        "雑談",
        "通勤",
        "天気",
        "雨対策"
    ),
    ConversationItem(
        "Thời tiết hôm nay nóng quá",
        "今日はとても暑いです",
        "雑談",
        "通勤",
        "天気",
        "暑さ"
    ),
    ConversationItem(
        "Tôi vừa đến công ty",
        "会社に着いたところです",
        "雑談",
        "通勤",
        "出社",
        "到着"
    ),
    ConversationItem(
        "Hôm nay tôi đến đúng giờ",
        "今日は時間通りに出社しました",
        "雑談",
        "通勤",
        "出社",
        "定時到着"
    ),
    ConversationItem(
        "Tôi đến muộn một chút",
        "少し遅れて到着しました",
        "雑談",
        "通勤",
        "出社",
        "遅刻"
    ),
    ConversationItem(
        "Bạn sống gần công ty không?",
        "会社の近くに住んでいますか？",
        "雑談",
        "通勤",
        "住まい",
        "居住地"
    ),
    ConversationItem(
        "Tôi sống cách công ty khoảng 10 km",
        "会社から約10kmの所に住んでいます",
        "雑談",
        "通勤",
        "住まい",
        "距離"
    ),
    ConversationItem(
        "Tôi muốn sống gần công ty hơn",
        "もっと会社の近くに住みたいです",
        "雑談",
        "通勤",
        "住まい",
        "希望"
    ),

    //
    // ✅ Part29 週末雑談
    //
    ConversationItem(
        "Cuối tuần của bạn thế nào?",
        "週末はどうでしたか？",
        "雑談",
        "週末",
        "週末確認",
        "週末"
    ),
    ConversationItem(
        "Bạn có kế hoạch gì cho cuối tuần không?",
        "週末の予定はありますか？",
        "雑談",
        "週末",
        "週末確認",
        "予定確認"
    ),
    ConversationItem(
        "Bạn thường làm gì vào cuối tuần?",
        "週末は普段何をしていますか？",
        "雑談",
        "週末",
        "週末確認",
        "習慣"
    ),
    ConversationItem(
        "Cuối tuần tôi đi chơi",
        "週末は遊びに行きました",
        "雑談",
        "週末",
        "外出",
        "外出"
    ),
    ConversationItem(
        "Tôi đi trung tâm thành phố",
        "市内中心部へ行きました",
        "雑談",
        "週末",
        "外出",
        "街歩き"
    ),
    ConversationItem(
        "Tôi đi phố đi bộ Nguyễn Huệ",
        "グエンフエ通りへ行きました",
        "雑談",
        "週末",
        "外出",
        "観光"
    ),
    ConversationItem(
        "Tôi đi cùng gia đình",
        "家族と出かけました",
        "雑談",
        "週末",
        "家族",
        "家族"
    ),
    ConversationItem(
        "Tôi ở nhà với gia đình",
        "家族と家で過ごしました",
        "雑談",
        "週末",
        "家族",
        "団らん"
    ),
    ConversationItem(
        "Tôi về thăm bố mẹ",
        "両親に会いに行きました",
        "雑談",
        "週末",
        "家族",
        "帰省"
    ),
    ConversationItem(
        "Tôi đi mua sắm",
        "買い物に行きました",
        "雑談",
        "週末",
        "買い物",
        "買い物"
    ),
    ConversationItem(
        "Tôi đi chợ",
        "市場へ行きました",
        "雑談",
        "週末",
        "買い物",
        "市場"
    ),
    ConversationItem(
        "Tôi mua vài món quà",
        "お土産をいくつか買いました",
        "雑談",
        "週末",
        "買い物",
        "お土産"
    ),
    ConversationItem(
        "Tôi nghỉ ngơi ở nhà",
        "家でゆっくり休みました",
        "雑談",
        "週末",
        "リラックス",
        "休養"
    ),
    ConversationItem(
        "Tôi xem phim ở nhà",
        "家で映画を見ました",
        "雑談",
        "週末",
        "リラックス",
        "映画"
    ),
    ConversationItem(
        "Tôi ngủ nhiều",
        "たくさん寝ました",
        "雑談",
        "週末",
        "リラックス",
        "睡眠"
    ),
    ConversationItem(
        "Cuối tuần rất vui",
        "とても楽しい週末でした",
        "雑談",
        "週末",
        "感想",
        "楽しかった"
    ),
    ConversationItem(
        "Tôi đã có một cuối tuần tuyệt vời",
        "素晴らしい週末でした",
        "雑談",
        "週末",
        "感想",
        "満足"
    ),
    ConversationItem(
        "Tôi muốn đi lần nữa",
        "また行きたいです",
        "雑談",
        "週末",
        "感想",
        "再訪"
    ),
    ConversationItem(
        "Cuối tuần này bạn muốn đi đâu?",
        "今週末はどこへ行きたいですか？",
        "雑談",
        "週末",
        "予定",
        "外出予定"
    ),
    ConversationItem(
        "Bạn có rảnh cuối tuần này không?",
        "今週末は空いていますか？",
        "雑談",
        "週末",
        "予定",
        "予定確認"
    ),
    ConversationItem(
        "Chúng ta đi cà phê cuối tuần nhé",
        "週末にカフェへ行きましょう",
        "雑談",
        "週末",
        "予定",
        "誘い"
    ),

    //
    // ✅ Part30 飲み会
    //
    ConversationItem(
        "Một, hai, ba, dô!",
        "乾杯！",
        "食事",
        "飲み会",
        "乾杯",
        "乾杯"
    ),
    ConversationItem(
        "Chúc sức khỏe!",
        "健康に乾杯！",
        "食事",
        "飲み会",
        "乾杯",
        "乾杯"
    ),
    ConversationItem(
        "Cạn ly nhé!",
        "飲み干しましょう！",
        "食事",
        "飲み会",
        "乾杯",
        "乾杯"
    ),
    ConversationItem(
        "Bạn uống bia không?",
        "ビールを飲みますか？",
        "食事",
        "飲み会",
        "飲み物",
        "ビール"
    ),
    ConversationItem(
        "Tôi uống một ly bia",
        "ビールを一杯飲みます",
        "食事",
        "飲み会",
        "飲み物",
        "注文"
    ),
    ConversationItem(
        "Tôi không uống được nhiều",
        "あまりお酒は強くありません",
        "食事",
        "飲み会",
        "飲み物",
        "酒量"
    ),
    ConversationItem(
        "Món này ngon quá",
        "この料理はとてもおいしいです",
        "食事",
        "飲み会",
        "料理",
        "感想"
    ),
    ConversationItem(
        "Bạn thử món này nhé",
        "この料理を食べてみてください",
        "食事",
        "飲み会",
        "料理",
        "おすすめ"
    ),
    ConversationItem(
        "Đây là món đặc sản",
        "これは名物料理です",
        "食事",
        "飲み会",
        "料理",
        "紹介"
    ),
    ConversationItem(
        "Bạn quê ở đâu?",
        "出身はどこですか？",
        "食事",
        "飲み会",
        "雑談",
        "出身地"
    ),
    ConversationItem(
        "Bạn làm ở công ty bao lâu rồi?",
        "この会社でどのくらい働いていますか？",
        "食事",
        "飲み会",
        "雑談",
        "仕事"
    ),
    ConversationItem(
        "Bạn có sở thích gì?",
        "趣味は何ですか？",
        "食事",
        "飲み会",
        "雑談",
        "趣味"
    ),
    ConversationItem(
        "Uống thêm một ly nhé",
        "もう一杯飲みましょう",
        "食事",
        "飲み会",
        "すすめる",
        "追加"
    ),
    ConversationItem(
        "Bạn muốn gọi thêm gì không?",
        "何か追加で注文しますか？",
        "食事",
        "飲み会",
        "すすめる",
        "追加注文"
    ),
    ConversationItem(
        "Chúng ta gọi thêm đồ ăn nhé",
        "料理を追加しましょう",
        "食事",
        "飲み会",
        "すすめる",
        "追加注文"
    ),
    ConversationItem(
        "Hôm nay rất vui",
        "今日はとても楽しいです",
        "食事",
        "飲み会",
        "感想",
        "楽しい"
    ),
    ConversationItem(
        "Cảm ơn vì bữa tối",
        "夕食をごちそうさまでした",
        "食事",
        "飲み会",
        "感想",
        "お礼"
    ),
    ConversationItem(
        "Tôi muốn đi nữa lần sau",
        "また次回も行きたいです",
        "食事",
        "飲み会",
        "感想",
        "再参加"
    ),
    ConversationItem(
        "Lần sau đi uống tiếp nhé",
        "また飲みに行きましょう",
        "食事",
        "飲み会",
        "次回の約束",
        "再会"
    ),
    ConversationItem(
        "Cuối tuần đi ăn nhé",
        "週末に食事に行きましょう",
        "食事",
        "飲み会",
        "次回の約束",
        "食事"
    ),
    ConversationItem(
        "Hẹn gặp lại nhé",
        "また会いましょう",
        "食事",
        "飲み会",
        "次回の約束",
        "別れ"
    ),

    //
    // ✅ Part31 趣味
    //
    ConversationItem(
        "Sở thích của bạn là gì?",
        "趣味は何ですか？",
        "雑談",
        "趣味",
        "質問",
        "趣味を聞く"
    ),
    ConversationItem(
        "Bạn thích làm gì khi rảnh?",
        "暇な時は何をするのが好きですか？",
        "雑談",
        "趣味",
        "質問",
        "趣味を聞く"
    ),
    ConversationItem(
        "Bạn có sở thích nào đặc biệt không?",
        "何か特別な趣味はありますか？",
        "雑談",
        "趣味",
        "質問",
        "趣味を聞く"
    ),
    ConversationItem(
        "Sở thích của tôi là xem phim",
        "私の趣味は映画鑑賞です",
        "雑談",
        "趣味",
        "紹介",
        "映画"
    ),
    ConversationItem(
        "Tôi thích nghe nhạc",
        "音楽を聴くのが好きです",
        "雑談",
        "趣味",
        "紹介",
        "音楽"
    ),
    ConversationItem(
        "Tôi thích đọc sách",
        "読書が好きです",
        "雑談",
        "趣味",
        "紹介",
        "読書"
    ),
    ConversationItem(
        "Bạn thích thể thao nào?",
        "どんなスポーツが好きですか？",
        "雑談",
        "趣味",
        "スポーツ",
        "スポーツ"
    ),
    ConversationItem(
        "Tôi thích bóng đá",
        "サッカーが好きです",
        "雑談",
        "趣味",
        "スポーツ",
        "サッカー"
    ),
    ConversationItem(
        "Tôi thường chạy bộ vào cuối tuần",
        "週末によくジョギングします",
        "雑談",
        "趣味",
        "スポーツ",
        "ランニング"
    ),
    ConversationItem(
        "Bạn có chơi game không?",
        "ゲームはしますか？",
        "雑談",
        "趣味",
        "ゲーム",
        "ゲーム"
    ),
    ConversationItem(
        "Tôi thích chơi game trên điện thoại",
        "スマホゲームが好きです",
        "雑談",
        "趣味",
        "ゲーム",
        "スマホゲーム"
    ),
    ConversationItem(
        "Bạn đang chơi game gì?",
        "今どんなゲームをしていますか？",
        "雑談",
        "趣味",
        "ゲーム",
        "ゲームの話"
    ),
    ConversationItem(
        "Bạn thích xem phim gì?",
        "どんな映画が好きですか？",
        "雑談",
        "趣味",
        "映画",
        "映画"
    ),
    ConversationItem(
        "Tôi thích phim hành động",
        "アクション映画が好きです",
        "雑談",
        "趣味",
        "映画",
        "映画ジャンル"
    ),
    ConversationItem(
        "Bộ phim đó rất hay",
        "その映画はとても面白いです",
        "雑談",
        "趣味",
        "映画",
        "映画感想"
    ),
    ConversationItem(
        "Bạn thích nghe loại nhạc nào?",
        "どんな音楽が好きですか？",
        "雑談",
        "趣味",
        "音楽",
        "音楽"
    ),
    ConversationItem(
        "Tôi thích nhạc Việt Nam",
        "ベトナム音楽が好きです",
        "雑談",
        "趣味",
        "音楽",
        "音楽の好み"
    ),
    ConversationItem(
        "Tôi thường nghe nhạc khi đi làm",
        "通勤中によく音楽を聴きます",
        "雑談",
        "趣味",
        "音楽",
        "習慣"
    ),
    ConversationItem(
        "Bạn thích đi du lịch không?",
        "旅行は好きですか？",
        "雑談",
        "趣味",
        "旅行",
        "旅行"
    ),
    ConversationItem(
        "Tôi thích đi du lịch",
        "旅行が好きです",
        "雑談",
        "趣味",
        "旅行",
        "旅行好き"
    ),
    ConversationItem(
        "Bạn đã đi Đà Nẵng chưa?",
        "ダナンへ行ったことがありますか？",
        "雑談",
        "趣味",
        "旅行",
        "旅行先"
    ),
    ConversationItem(
        "Chúng ta có cùng sở thích",
        "私たちは同じ趣味ですね",
        "雑談",
        "趣味",
        "共感",
        "共感"
    ),
    ConversationItem(
        "Tôi cũng thích điều đó",
        "私もそれが好きです",
        "雑談",
        "趣味",
        "共感",
        "共感"
    ),
    ConversationItem(
        "Nghe thú vị đấy",
        "面白そうですね",
        "雑談",
        "趣味",
        "共感",
        "反応"
    ),

    //
    // ✅ Part32 家族
    //
    ConversationItem(
        "Gia đình bạn có mấy người?",
        "ご家族は何人ですか？",
        "雑談",
        "家族",
        "家族構成",
        "人数"
    ),
    ConversationItem(
        "Bạn có anh chị em không?",
        "兄弟姉妹はいますか？",
        "雑談",
        "家族",
        "家族構成",
        "兄弟姉妹"
    ),
    ConversationItem(
        "Bạn là con thứ mấy trong gia đình?",
        "何番目の子どもですか？",
        "雑談",
        "家族",
        "家族構成",
        "兄弟順"
    ),
    ConversationItem(
        "Bạn đã kết hôn chưa?",
        "結婚していますか？",
        "雑談",
        "家族",
        "結婚",
        "結婚"
    ),
    ConversationItem(
        "Tôi đã kết hôn rồi",
        "私は結婚しています",
        "雑談",
        "家族",
        "結婚",
        "既婚"
    ),
    ConversationItem(
        "Tôi chưa kết hôn",
        "私はまだ結婚していません",
        "雑談",
        "家族",
        "結婚",
        "未婚"
    ),
    ConversationItem(
        "Bạn có con không?",
        "お子さんはいますか？",
        "雑談",
        "家族",
        "子ども",
        "子ども"
    ),
    ConversationItem(
        "Tôi có hai con",
        "子どもが二人います",
        "雑談",
        "家族",
        "子ども",
        "子ども"
    ),
    ConversationItem(
        "Con bạn bao nhiêu tuổi?",
        "お子さんは何歳ですか？",
        "雑談",
        "家族",
        "子ども",
        "年齢"
    ),
    ConversationItem(
        "Bố mẹ bạn vẫn khỏe chứ?",
        "ご両親はお元気ですか？",
        "雑談",
        "家族",
        "両親",
        "近況"
    ),
    ConversationItem(
        "Bố mẹ tôi sống ở quê",
        "両親は実家に住んでいます",
        "雑談",
        "家族",
        "両親",
        "居住地"
    ),
    ConversationItem(
        "Tôi thường về thăm bố mẹ",
        "よく両親に会いに行きます",
        "雑談",
        "家族",
        "両親",
        "帰省"
    ),
    ConversationItem(
        "Gia đình tôi sống ở Thành phố Hồ Chí Minh",
        "家族はホーチミン市に住んでいます",
        "雑談",
        "家族",
        "居住地",
        "居住地"
    ),
    ConversationItem(
        "Gia đình tôi sống ở Hà Nội",
        "家族はハノイに住んでいます",
        "雑談",
        "家族",
        "居住地",
        "居住地"
    ),
    ConversationItem(
        "Gia đình tôi sống ở quê",
        "家族は実家に住んでいます",
        "雑談",
        "家族",
        "居住地",
        "実家"
    ),
    ConversationItem(
        "Cuối tuần tôi dành thời gian cho gia đình",
        "週末は家族と過ごします",
        "雑談",
        "家族",
        "休日",
        "週末"
    ),
    ConversationItem(
        "Tôi đi ăn cùng gia đình",
        "家族と食事に行きます",
        "雑談",
        "家族",
        "休日",
        "外食"
    ),
    ConversationItem(
        "Tôi đưa gia đình đi chơi",
        "家族を連れて出かけます",
        "雑談",
        "家族",
        "休日",
        "外出"
    ),
    ConversationItem(
        "Gia đình rất quan trọng đối với tôi",
        "家族は私にとってとても大切です",
        "雑談",
        "家族",
        "気持ち",
        "家族愛"
    ),
    ConversationItem(
        "Tôi tự hào về gia đình mình",
        "自分の家族を誇りに思っています",
        "雑談",
        "家族",
        "気持ち",
        "誇り"
    ),
    ConversationItem(
        "Tôi rất yêu gia đình mình",
        "私は家族をとても愛しています",
        "雑談",
        "家族",
        "気持ち",
        "愛情"
    ),
    ConversationItem(
        "Bạn có thường gọi điện cho gia đình không?",
        "よく家族に電話しますか？",
        "雑談",
        "家族",
        "連絡",
        "電話"
    ),
    ConversationItem(
        "Tôi gọi điện cho gia đình mỗi tuần",
        "毎週家族に電話します",
        "雑談",
        "家族",
        "連絡",
        "連絡"
    ),
    ConversationItem(
        "Hôm qua tôi vừa nói chuyện với gia đình",
        "昨日家族と話しました",
        "雑談",
        "家族",
        "連絡",
        "雑談"
    ),

    //
    // ✅ Part33 異性との雑談
    //
    ConversationItem(
        "Bạn tên là gì?",
        "お名前は何ですか？",
        "雑談",
        "異性",
        "自己紹介",
        "名前"
    ),
    ConversationItem(
        "Bạn đến từ đâu?",
        "どちらの出身ですか？",
        "雑談",
        "異性",
        "自己紹介",
        "出身"
    ),
    ConversationItem(
        "Rất vui được gặp bạn",
        "お会いできてうれしいです",
        "雑談",
        "異性",
        "自己紹介",
        "挨拶"
    ),
    ConversationItem(
        "Sở thích của bạn là gì?",
        "趣味は何ですか？",
        "雑談",
        "異性",
        "趣味",
        "趣味"
    ),
    ConversationItem(
        "Bạn thích xem phim không?",
        "映画は好きですか？",
        "雑談",
        "異性",
        "趣味",
        "映画"
    ),
    ConversationItem(
        "Bạn thường làm gì khi rảnh?",
        "暇な時は何をしていますか？",
        "雑談",
        "異性",
        "趣味",
        "休日"
    ),
    ConversationItem(
        "Bạn thích món ăn nào?",
        "好きな食べ物は何ですか？",
        "雑談",
        "異性",
        "食べ物",
        "好み"
    ),
    ConversationItem(
        "Bạn thích món Việt nào nhất?",
        "好きなベトナム料理は何ですか？",
        "雑談",
        "異性",
        "食べ物",
        "ベトナム料理"
    ),
    ConversationItem(
        "Bạn có thích đồ ăn Nhật không?",
        "日本料理は好きですか？",
        "雑談",
        "異性",
        "食べ物",
        "日本料理"
    ),
    ConversationItem(
        "Cuối tuần bạn thường làm gì?",
        "週末は普段何をしていますか？",
        "雑談",
        "異性",
        "週末",
        "週末"
    ),
    ConversationItem(
        "Cuối tuần này bạn có kế hoạch gì không?",
        "今週末の予定はありますか？",
        "雑談",
        "異性",
        "週末",
        "予定"
    ),
    ConversationItem(
        "Cuối tuần vừa rồi bạn đi đâu?",
        "先週末はどこへ行きましたか？",
        "雑談",
        "異性",
        "週末",
        "外出"
    ),
    ConversationItem(
        "Bạn có quán cà phê nào giới thiệu không?",
        "おすすめのカフェはありますか？",
        "雑談",
        "異性",
        "カフェ",
        "おすすめ"
    ),
    ConversationItem(
        "Bạn thường đến quán cà phê nào?",
        "普段どこのカフェに行きますか？",
        "雑談",
        "異性",
        "カフェ",
        "カフェ"
    ),
    ConversationItem(
        "Chúng ta đi uống cà phê nhé",
        "コーヒーを飲みに行きましょう",
        "雑談",
        "異性",
        "カフェ",
        "誘い"
    ),
    ConversationItem(
        "Bạn thích nơi nào ở Thành phố Hồ Chí Minh?",
        "ホーチミンで好きな場所はどこですか？",
        "雑談",
        "異性",
        "おすすめスポット",
        "好きな場所"
    ),
    ConversationItem(
        "Bạn có thể giới thiệu chỗ nào hay không?",
        "おすすめの場所を教えてもらえますか？",
        "雑談",
        "異性",
        "おすすめスポット",
        "紹介"
    ),
    ConversationItem(
        "Tôi muốn đi chỗ đó một lần",
        "そこに一度行ってみたいです",
        "雑談",
        "異性",
        "おすすめスポット",
        "興味"
    ),
    ConversationItem(
        "Bạn sống ở Thành phố Hồ Chí Minh lâu chưa?",
        "ホーチミンには長く住んでいますか？",
        "雑談",
        "異性",
        "生活",
        "居住期間"
    ),
    ConversationItem(
        "Bạn thích cuộc sống ở đây không?",
        "ここの生活は好きですか？",
        "雑談",
        "異性",
        "生活",
        "生活"
    ),
    ConversationItem(
        "Tôi thích cuộc sống ở Việt Nam",
        "ベトナムでの生活が好きです",
        "雑談",
        "異性",
        "生活",
        "生活感想"
    ),
    ConversationItem(
        "Nói chuyện với bạn rất vui",
        "お話しできて楽しかったです",
        "雑談",
        "異性",
        "会話締め",
        "楽しかった"
    ),
    ConversationItem(
        "Cảm ơn vì cuộc trò chuyện",
        "お話ししてくれてありがとうございます",
        "雑談",
        "異性",
        "会話締め",
        "お礼"
    ),
    ConversationItem(
        "Hy vọng sẽ gặp lại bạn",
        "またお会いできるといいですね",
        "雑談",
        "異性",
        "会話締め",
        "別れ"
    ),

    //
    // ✅ Part34 ベトナム文化
    //
    ConversationItem(
        "Bạn thường làm gì vào dịp Tết?",
        "テトには何をしますか？",
        "文化",
        "ベトナム文化",
        "テト",
        "テトの過ごし方"
    ),
    ConversationItem(
        "Bạn có về quê ăn Tết không?",
        "テトには帰省しますか？",
        "文化",
        "ベトナム文化",
        "テト",
        "帰省"
    ),
    ConversationItem(
        "Tết là ngày lễ quan trọng nhất phải không?",
        "テトは一番大切な祝日ですよね？",
        "文化",
        "ベトナム文化",
        "テト",
        "祝日"
    ),
    ConversationItem(
        "Quê của bạn ở đâu?",
        "出身はどこですか？",
        "文化",
        "ベトナム文化",
        "故郷",
        "出身地"
    ),
    ConversationItem(
        "Tôi muốn đến thăm quê bạn",
        "あなたの故郷を訪れてみたいです",
        "文化",
        "ベトナム文化",
        "故郷",
        "興味"
    ),
    ConversationItem(
        "Quê bạn nổi tiếng về gì?",
        "あなたの故郷は何で有名ですか？",
        "文化",
        "ベトナム文化",
        "故郷",
        "名物"
    ),
    ConversationItem(
        "Món ăn Việt Nam nào nổi tiếng nhất?",
        "有名なベトナム料理は何ですか？",
        "文化",
        "ベトナム文化",
        "食文化",
        "料理"
    ),
    ConversationItem(
        "Tôi thích phở Việt Nam",
        "ベトナムのフォーが好きです",
        "文化",
        "ベトナム文化",
        "食文化",
        "好み"
    ),
    ConversationItem(
        "Bạn thường ăn món gì vào dịp lễ?",
        "祝日には何を食べますか？",
        "文化",
        "ベトナム文化",
        "食文化",
        "行事食"
    ),
    ConversationItem(
        "Người Việt thường uống cà phê phải không?",
        "ベトナム人はよくコーヒーを飲みますよね？",
        "文化",
        "ベトナム文化",
        "習慣",
        "コーヒー文化"
    ),
    ConversationItem(
        "Bạn thường dậy lúc mấy giờ?",
        "普段何時に起きますか？",
        "文化",
        "ベトナム文化",
        "習慣",
        "生活習慣"
    ),
    ConversationItem(
        "Bạn thường làm gì sau giờ làm?",
        "仕事の後は何をしていますか？",
        "文化",
        "ベトナム文化",
        "習慣",
        "日常生活"
    ),
    ConversationItem(
        "Ở Việt Nam có ngày lễ nào nổi tiếng?",
        "ベトナムにはどんな有名な祝日がありますか？",
        "文化",
        "ベトナム文化",
        "祝日",
        "祝日"
    ),
    ConversationItem(
        "Bạn nghỉ mấy ngày vào dịp Tết?",
        "テトは何日休みますか？",
        "文化",
        "ベトナム文化",
        "祝日",
        "休暇"
    ),
    ConversationItem(
        "Bạn thích ngày lễ nào nhất?",
        "一番好きな祝日は何ですか？",
        "文化",
        "ベトナム文化",
        "祝日",
        "好み"
    ),
    ConversationItem(
        "Tôi thích Thành phố Hồ Chí Minh",
        "ホーチミン市が好きです",
        "文化",
        "ベトナム文化",
        "ベトナムの魅力",
        "ホーチミン"
    ),
    ConversationItem(
        "Việt Nam có nhiều nơi đẹp",
        "ベトナムにはきれいな場所がたくさんありますね",
        "文化",
        "ベトナム文化",
        "ベトナムの魅力",
        "観光"
    ),
    ConversationItem(
        "Tôi muốn khám phá nhiều nơi hơn",
        "もっといろいろな場所を訪れたいです",
        "文化",
        "ベトナム文化",
        "ベトナムの魅力",
        "旅行"
    ),
    ConversationItem(
        "Bạn có thể giới thiệu văn hóa Việt Nam không?",
        "ベトナム文化について教えてもらえますか？",
        "文化",
        "ベトナム文化",
        "文化紹介",
        "文化を聞く"
    ),
    ConversationItem(
        "Tôi đang học về văn hóa Việt Nam",
        "ベトナム文化を勉強しています",
        "文化",
        "ベトナム文化",
        "文化紹介",
        "学習"
    ),
    ConversationItem(
        "Văn hóa Việt Nam rất thú vị",
        "ベトナム文化はとても興味深いです",
        "文化",
        "ベトナム文化",
        "文化紹介",
        "感想"
    ),

    //
    // ✅ Part35 マッサージ
    //
    ConversationItem(
        "Tôi muốn đặt lịch massage",
        "マッサージを予約したいです",
        "娯楽",
        "マッサージ",
        "予約",
        "予約"
    ),
    ConversationItem(
        "Hôm nay còn chỗ không?",
        "今日は空いていますか？",
        "娯楽",
        "マッサージ",
        "予約",
        "空き確認"
    ),
    ConversationItem(
        "Tôi muốn massage lúc 7 giờ",
        "7時に予約したいです",
        "娯楽",
        "マッサージ",
        "予約",
        "時間指定"
    ),
    ConversationItem(
        "Bạn có những loại massage nào?",
        "どのようなマッサージがありますか？",
        "娯楽",
        "マッサージ",
        "コース選択",
        "コース確認"
    ),
    ConversationItem(
        "Tôi muốn massage toàn thân",
        "全身マッサージをお願いします",
        "娯楽",
        "マッサージ",
        "コース選択",
        "全身"
    ),
    ConversationItem(
        "Tôi muốn massage chân",
        "フットマッサージをお願いします",
        "娯楽",
        "マッサージ",
        "コース選択",
        "足"
    ),
    ConversationItem(
        "Massage trong bao lâu?",
        "マッサージは何分ですか？",
        "娯楽",
        "マッサージ",
        "時間確認",
        "施術時間"
    ),
    ConversationItem(
        "Tôi muốn gói 60 phút",
        "60分コースをお願いします",
        "娯楽",
        "マッサージ",
        "時間確認",
        "60分"
    ),
    ConversationItem(
        "Có gói 90 phút không?",
        "90分コースはありますか？",
        "娯楽",
        "マッサージ",
        "時間確認",
        "90分"
    ),
    ConversationItem(
        "Vai tôi hơi đau",
        "肩が少し凝っています",
        "娯楽",
        "マッサージ",
        "症状説明",
        "肩こり"
    ),
    ConversationItem(
        "Lưng tôi bị mỏi",
        "腰が疲れています",
        "娯楽",
        "マッサージ",
        "症状説明",
        "腰"
    ),
    ConversationItem(
        "Chân tôi hơi đau",
        "足が少し痛いです",
        "娯楽",
        "マッサージ",
        "症状説明",
        "足"
    ),
    ConversationItem(
        "Mạnh hơn một chút được không?",
        "もう少し強くできますか？",
        "娯楽",
        "マッサージ",
        "強さ調整",
        "強め"
    ),
    ConversationItem(
        "Nhẹ hơn một chút nhé",
        "もう少し弱めでお願いします",
        "娯楽",
        "マッサージ",
        "強さ調整",
        "弱め"
    ),
    ConversationItem(
        "Thế này vừa rồi",
        "これくらいでちょうどいいです",
        "娯楽",
        "マッサージ",
        "強さ調整",
        "ちょうど良い"
    ),
    ConversationItem(
        "Tôi muốn tập trung vào vai",
        "肩を重点的にお願いします",
        "娯楽",
        "マッサージ",
        "部位指定",
        "肩"
    ),
    ConversationItem(
        "Xin massage chân nhiều hơn",
        "足を多めにお願いします",
        "娯楽",
        "マッサージ",
        "部位指定",
        "足"
    ),
    ConversationItem(
        "Xin massage lưng trước",
        "先に背中をお願いします",
        "娯楽",
        "マッサージ",
        "部位指定",
        "背中"
    ),
    ConversationItem(
        "Thoải mái quá",
        "とても気持ちいいです",
        "娯楽",
        "マッサージ",
        "感想",
        "気持ちいい"
    ),
    ConversationItem(
        "Tôi cảm thấy dễ chịu hơn",
        "楽になりました",
        "娯楽",
        "マッサージ",
        "感想",
        "効果"
    ),
    ConversationItem(
        "Massage rất tốt",
        "とても良かったです",
        "娯楽",
        "マッサージ",
        "感想",
        "評価"
    ),
    ConversationItem(
        "Hết bao nhiêu tiền?",
        "いくらですか？",
        "娯楽",
        "マッサージ",
        "支払い",
        "料金"
    ),
    ConversationItem(
        "Tôi trả bằng thẻ",
        "カードで支払います",
        "娯楽",
        "マッサージ",
        "支払い",
        "カード"
    ),
    ConversationItem(
        "Tôi trả bằng tiền mặt",
        "現金で支払います",
        "娯楽",
        "マッサージ",
        "支払い",
        "現金"
    ),
    ConversationItem(
        "Tôi sẽ quay lại lần sau",
        "また来ます",
        "娯楽",
        "マッサージ",
        "退店",
        "再来店"
    ),
    ConversationItem(
        "Cảm ơn rất nhiều",
        "ありがとうございました",
        "娯楽",
        "マッサージ",
        "退店",
        "お礼"
    ),
    ConversationItem(
        "Hẹn gặp lại",
        "またお会いしましょう",
        "娯楽",
        "マッサージ",
        "退店",
        "別れ"
    ),

    //
    // ✅ Part36 理容店・美容院
    //
    ConversationItem(
        "Tôi muốn cắt tóc",
        "髪を切りたいです",
        "娯楽",
        "理容店・美容院",
        "受付",
        "散髪"
    ),
    ConversationItem(
        "Hôm nay còn chỗ không?",
        "今日は空いていますか？",
        "娯楽",
        "理容店・美容院",
        "受付",
        "空き確認"
    ),
    ConversationItem(
        "Tôi có đặt lịch rồi",
        "予約しています",
        "娯楽",
        "理容店・美容院",
        "受付",
        "予約"
    ),
    ConversationItem(
        "Tôi muốn cắt tóc ngắn",
        "短髪にしたいです",
        "娯楽",
        "理容店・美容院",
        "希望説明",
        "短髪"
    ),
    ConversationItem(
        "Tôi muốn tóc gọn gàng hơn",
        "もう少しすっきりした髪型にしたいです",
        "娯楽",
        "理容店・美容院",
        "希望説明",
        "希望"
    ),
    ConversationItem(
        "Giữ kiểu tóc hiện tại nhé",
        "今の髪型を維持してください",
        "娯楽",
        "理容店・美容院",
        "希望説明",
        "現状維持"
    ),
    ConversationItem(
        "Cắt ngắn một chút nhé",
        "少し短くしてください",
        "娯楽",
        "理容店・美容院",
        "長さ指定",
        "少し短く"
    ),
    ConversationItem(
        "Cắt ngắn hơn nữa",
        "もっと短くしてください",
        "娯楽",
        "理容店・美容院",
        "長さ指定",
        "短く"
    ),
    ConversationItem(
        "Đừng cắt quá ngắn",
        "短くしすぎないでください",
        "娯楽",
        "理容店・美容院",
        "長さ指定",
        "長さ調整"
    ),
    ConversationItem(
        "Cắt hai bên ngắn hơn nhé",
        "横をもう少し短くしてください",
        "娯楽",
        "理容店・美容院",
        "部位指定",
        "横"
    ),
    ConversationItem(
        "Để phần mái dài hơn",
        "前髪は長めにしてください",
        "娯楽",
        "理容店・美容院",
        "部位指定",
        "前髪"
    ),
    ConversationItem(
        "Tỉa phía sau giúp tôi",
        "後ろを整えてください",
        "娯楽",
        "理容店・美容院",
        "部位指定",
        "後ろ"
    ),
    ConversationItem(
        "Tôi muốn gội đầu",
        "シャンプーをお願いします",
        "娯楽",
        "理容店・美容院",
        "追加サービス",
        "シャンプー"
    ),
    ConversationItem(
        "Tôi muốn cạo râu",
        "髭剃りをお願いします",
        "娯楽",
        "理容店・美容院",
        "追加サービス",
        "髭剃り"
    ),
    ConversationItem(
        "Tôi muốn massage đầu",
        "ヘッドマッサージをお願いします",
        "娯楽",
        "理容店・美容院",
        "追加サービス",
        "ヘッドマッサージ"
    ),
    ConversationItem(
        "Tôi muốn nhuộm tóc",
        "髪を染めたいです",
        "娯楽",
        "理容店・美容院",
        "カラー",
        "カラー"
    ),
    ConversationItem(
        "Màu nào phù hợp với tôi?",
        "どの色が合いますか？",
        "娯楽",
        "理容店・美容院",
        "カラー",
        "相談"
    ),
    ConversationItem(
        "Tôi muốn nhuộm màu nâu",
        "茶色に染めたいです",
        "娯楽",
        "理容店・美容院",
        "カラー",
        "色指定"
    ),
    ConversationItem(
        "Tôi muốn uốn tóc",
        "パーマをかけたいです",
        "娯楽",
        "理容店・美容院",
        "パーマ",
        "パーマ"
    ),
    ConversationItem(
        "Mất bao lâu?",
        "どれくらい時間がかかりますか？",
        "娯楽",
        "理容店・美容院",
        "パーマ",
        "時間確認"
    ),
    ConversationItem(
        "Tôi muốn kiểu tự nhiên",
        "自然な仕上がりにしたいです",
        "娯楽",
        "理容店・美容院",
        "パーマ",
        "仕上がり"
    ),
    ConversationItem(
        "Cho tôi xem phía sau được không?",
        "後ろを見せてもらえますか？",
        "娯楽",
        "理容店・美容院",
        "仕上がり確認",
        "確認"
    ),
    ConversationItem(
        "Có thể ngắn hơn một chút không?",
        "もう少し短くできますか？",
        "娯楽",
        "理容店・美容院",
        "仕上がり確認",
        "再調整"
    ),
    ConversationItem(
        "Tôi rất hài lòng",
        "とても満足しています",
        "娯楽",
        "理容店・美容院",
        "仕上がり確認",
        "満足"
    ),
    ConversationItem(
        "Cắt tóc hết bao nhiêu tiền?",
        "散髪はいくらですか？",
        "娯楽",
        "理容店・美容院",
        "支払い",
        "料金"
    ),
    ConversationItem(
        "Tôi trả bằng thẻ",
        "カードで支払います",
        "娯楽",
        "理容店・美容院",
        "支払い",
        "カード"
    ),
    ConversationItem(
        "Tôi trả bằng tiền mặt",
        "現金で支払います",
        "娯楽",
        "理容店・美容院",
        "支払い",
        "現金"
    ),
    ConversationItem(
        "Cảm ơn, tôi thích kiểu tóc này",
        "ありがとう、この髪型が気に入りました",
        "娯楽",
        "理容店・美容院",
        "退店",
        "満足"
    ),
    ConversationItem(
        "Tôi sẽ quay lại lần sau",
        "また来ます",
        "娯楽",
        "理容店・美容院",
        "退店",
        "再来店"
    ),
    ConversationItem(
        "Hẹn gặp lại",
        "またお会いしましょう",
        "娯楽",
        "理容店・美容院",
        "退店",
        "別れ"
    ),

    //
    // ✅ Part37 洗濯・ランドリー
    //
    ConversationItem(
        "Tôi muốn giặt quần áo",
        "洗濯をお願いしたいです",
        "住居",
        "洗濯・ランドリー",
        "受付",
        "洗濯依頼"
    ),
    ConversationItem(
        "Hôm nay có nhận giặt không?",
        "今日は洗濯を受け付けていますか？",
        "住居",
        "洗濯・ランドリー",
        "受付",
        "受付確認"
    ),
    ConversationItem(
        "Tôi có quần áo cần giặt",
        "洗濯したい服があります",
        "住居",
        "洗濯・ランドリー",
        "受付",
        "依頼"
    ),
    ConversationItem(
        "Bao giờ xong?",
        "いつ出来上がりますか？",
        "住居",
        "洗濯・ランドリー",
        "納期確認",
        "完了予定"
    ),
    ConversationItem(
        "Tôi có thể nhận vào ngày mai không?",
        "明日受け取れますか？",
        "住居",
        "洗濯・ランドリー",
        "納期確認",
        "受取日"
    ),
    ConversationItem(
        "Mất bao lâu để giặt?",
        "洗濯にどれくらい時間がかかりますか？",
        "住居",
        "洗濯・ランドリー",
        "納期確認",
        "所要時間"
    ),
    ConversationItem(
        "Tôi muốn giặt và sấy",
        "洗濯と乾燥をお願いします",
        "住居",
        "洗濯・ランドリー",
        "サービス選択",
        "洗濯乾燥"
    ),
    ConversationItem(
        "Tôi chỉ muốn giặt thôi",
        "洗濯だけお願いします",
        "住居",
        "洗濯・ランドリー",
        "サービス選択",
        "洗濯のみ"
    ),
    ConversationItem(
        "Tôi muốn ủi quần áo",
        "アイロンがけもお願いします",
        "住居",
        "洗濯・ランドリー",
        "サービス選択",
        "アイロン"
    ),
    ConversationItem(
        "Xin giặt riêng áo này",
        "この服は別で洗ってください",
        "住居",
        "洗濯・ランドリー",
        "要望",
        "個別洗濯"
    ),
    ConversationItem(
        "Xin cẩn thận với áo trắng",
        "白い服は注意して洗ってください",
        "住居",
        "洗濯・ランドリー",
        "要望",
        "白物"
    ),
    ConversationItem(
        "Quần áo này dễ hỏng",
        "この服は傷みやすいです",
        "住居",
        "洗濯・ランドリー",
        "要望",
        "注意喚起"
    ),
    ConversationItem(
        "Tôi muốn giặt gấp",
        "急ぎでお願いしたいです",
        "住居",
        "洗濯・ランドリー",
        "特急依頼",
        "急ぎ"
    ),
    ConversationItem(
        "Hôm nay lấy được không?",
        "今日受け取れますか？",
        "住居",
        "洗濯・ランドリー",
        "特急依頼",
        "当日受取"
    ),
    ConversationItem(
        "Tôi cần quần áo này vào tối nay",
        "今夜この服が必要です",
        "住居",
        "洗濯・ランドリー",
        "特急依頼",
        "緊急"
    ),
    ConversationItem(
        "Quần áo đã giặt xong chưa?",
        "洗濯は終わりましたか？",
        "住居",
        "洗濯・ランドリー",
        "受取",
        "完了確認"
    ),
    ConversationItem(
        "Tôi đến lấy quần áo",
        "洗濯物を受け取りに来ました",
        "住居",
        "洗濯・ランドリー",
        "受取",
        "受取"
    ),
    ConversationItem(
        "Đây có phải đồ của tôi không?",
        "これは私の洗濯物ですか？",
        "住居",
        "洗濯・ランドリー",
        "受取",
        "確認"
    ),
    ConversationItem(
        "Áo này chưa sạch",
        "この服はまだきれいになっていません",
        "住居",
        "洗濯・ランドリー",
        "トラブル",
        "再洗濯"
    ),
    ConversationItem(
        "Tôi bị thiếu một chiếc áo",
        "シャツが1枚足りません",
        "住居",
        "洗濯・ランドリー",
        "トラブル",
        "紛失"
    ),
    ConversationItem(
        "Quần áo này không phải của tôi",
        "この服は私のものではありません",
        "住居",
        "洗濯・ランドリー",
        "トラブル",
        "取り違え"
    ),
    ConversationItem(
        "Hết bao nhiêu tiền?",
        "いくらですか？",
        "住居",
        "洗濯・ランドリー",
        "支払い",
        "料金"
    ),
    ConversationItem(
        "Tôi trả bằng tiền mặt",
        "現金で支払います",
        "住居",
        "洗濯・ランドリー",
        "支払い",
        "現金"
    ),
    ConversationItem(
        "Tôi trả bằng thẻ",
        "カードで支払います",
        "住居",
        "洗濯・ランドリー",
        "支払い",
        "カード"
    ),
    ConversationItem(
        "Quần áo thơm quá",
        "服がとても良い香りです",
        "住居",
        "洗濯・ランドリー",
        "感想",
        "良い仕上がり"
    ),
    ConversationItem(
        "Dịch vụ rất tốt",
        "とても良いサービスでした",
        "住居",
        "洗濯・ランドリー",
        "感想",
        "評価"
    ),
    ConversationItem(
        "Tôi sẽ quay lại lần sau",
        "また利用します",
        "住居",
        "洗濯・ランドリー",
        "感想",
        "再利用"
    ),

    //
    // ✅ Part38 コンドミニアム管理室
    //
    ConversationItem(
        "Tôi là cư dân ở đây",
        "私はこのマンションの住人です",
        "住居",
        "コンドミニアム管理室",
        "受付",
        "住人"
    ),
    ConversationItem(
        "Tôi cần hỗ trợ",
        "サポートをお願いしたいです",
        "住居",
        "コンドミニアム管理室",
        "受付",
        "相談"
    ),
    ConversationItem(
        "Tôi muốn gặp ban quản lý",
        "管理事務所の方と話したいです",
        "住居",
        "コンドミニアム管理室",
        "受付",
        "問い合わせ"
    ),
    ConversationItem(
        "Điều hòa trong phòng không hoạt động",
        "部屋のエアコンが動きません",
        "住居",
        "コンドミニアム管理室",
        "設備トラブル",
        "エアコン"
    ),
    ConversationItem(
        "Nước nóng không ra",
        "お湯が出ません",
        "住居",
        "コンドミニアム管理室",
        "設備トラブル",
        "給湯"
    ),
    ConversationItem(
        "Điện bị mất rồi",
        "停電しています",
        "住居",
        "コンドミニアム管理室",
        "設備トラブル",
        "停電"
    ),
    ConversationItem(
        "Tôi muốn yêu cầu sửa chữa",
        "修理を依頼したいです",
        "住居",
        "コンドミニアム管理室",
        "修理依頼",
        "修理"
    ),
    ConversationItem(
        "Khi nào nhân viên có thể đến?",
        "スタッフはいつ来られますか？",
        "住居",
        "コンドミニアム管理室",
        "修理依頼",
        "訪問時間"
    ),
    ConversationItem(
        "Xin kiểm tra giúp tôi",
        "確認をお願いします",
        "住居",
        "コンドミニアム管理室",
        "修理依頼",
        "点検"
    ),
    ConversationItem(
        "Tôi có bưu kiện không?",
        "荷物は届いていますか？",
        "住居",
        "コンドミニアム管理室",
        "荷物",
        "荷物確認"
    ),
    ConversationItem(
        "Tôi đến nhận bưu kiện",
        "荷物を受け取りに来ました",
        "住居",
        "コンドミニアム管理室",
        "荷物",
        "受取"
    ),
    ConversationItem(
        "Có hàng giao cho tôi không?",
        "私宛の配達物はありますか？",
        "住居",
        "コンドミニアム管理室",
        "荷物",
        "配達物"
    ),
    ConversationItem(
        "Xe máy của tôi đỗ ở tầng nào?",
        "私のバイクは何階に停めていますか？",
        "住居",
        "コンドミニアム管理室",
        "駐車場",
        "駐車場所"
    ),
    ConversationItem(
        "Tôi bị mất thẻ gửi xe",
        "駐車カードをなくしました",
        "住居",
        "コンドミニアム管理室",
        "駐車場",
        "カード紛失"
    ),
    ConversationItem(
        "Còn chỗ đỗ xe không?",
        "駐車スペースはありますか？",
        "住居",
        "コンドミニアム管理室",
        "駐車場",
        "空き確認"
    ),
    ConversationItem(
        "Hồ bơi mở cửa lúc mấy giờ?",
        "プールは何時から利用できますか？",
        "住居",
        "コンドミニアム管理室",
        "共用施設",
        "プール"
    ),
    ConversationItem(
        "Phòng gym ở đâu?",
        "ジムはどこですか？",
        "住居",
        "コンドミニアム管理室",
        "共用施設",
        "ジム"
    ),
    ConversationItem(
        "Tôi có thể sử dụng phòng sinh hoạt chung không?",
        "共用ラウンジを利用できますか？",
        "住居",
        "コンドミニアム管理室",
        "共用施設",
        "ラウンジ"
    ),
    ConversationItem(
        "Tôi quên thẻ ra vào",
        "入館カードを忘れました",
        "住居",
        "コンドミニアム管理室",
        "セキュリティ",
        "カード忘れ"
    ),
    ConversationItem(
        "Tôi không mở được cửa",
        "ドアが開きません",
        "住居",
        "コンドミニアム管理室",
        "セキュリティ",
        "ドア"
    ),
    ConversationItem(
        "Có người lạ ở tầng của tôi",
        "私の階に見知らぬ人がいます",
        "住居",
        "コンドミニアム管理室",
        "セキュリティ",
        "不審者"
    ),
    ConversationItem(
        "Cảm ơn đã hỗ trợ",
        "対応ありがとうございます",
        "住居",
        "コンドミニアム管理室",
        "お礼",
        "お礼"
    ),
    ConversationItem(
        "Vấn đề đã được giải quyết",
        "問題は解決しました",
        "住居",
        "コンドミニアム管理室",
        "お礼",
        "解決"
    ),
    ConversationItem(
        "Tôi rất hài lòng với sự hỗ trợ",
        "サポートに満足しています",
        "住居",
        "コンドミニアム管理室",
        "お礼",
        "満足"
    ),

    //
    // ✅ Part39 病院・クリニック
    //
    ConversationItem(
        "Tôi muốn khám bệnh",
        "診察を受けたいです",
        "医療",
        "病院・クリニック",
        "受付",
        "診察"
    ),
    ConversationItem(
        "Tôi có hẹn trước",
        "予約しています",
        "医療",
        "病院・クリニック",
        "受付",
        "予約"
    ),
    ConversationItem(
        "Tôi đến khám lần đầu",
        "初めて受診します",
        "医療",
        "病院・クリニック",
        "受付",
        "初診"
    ),
    ConversationItem(
        "Tôi bị sốt",
        "熱があります",
        "医療",
        "病院・クリニック",
        "症状説明",
        "発熱"
    ),
    ConversationItem(
        "Tôi bị đau đầu",
        "頭が痛いです",
        "医療",
        "病院・クリニック",
        "症状説明",
        "頭痛"
    ),
    ConversationItem(
        "Tôi bị đau bụng",
        "お腹が痛いです",
        "医療",
        "病院・クリニック",
        "症状説明",
        "腹痛"
    ),
    ConversationItem(
        "Tôi bị ho",
        "咳が出ます",
        "医療",
        "病院・クリニック",
        "体調説明",
        "咳"
    ),
    ConversationItem(
        "Tôi bị đau họng",
        "喉が痛いです",
        "医療",
        "病院・クリニック",
        "体調説明",
        "喉"
    ),
    ConversationItem(
        "Tôi cảm thấy mệt",
        "体がだるいです",
        "医療",
        "病院・クリニック",
        "体調説明",
        "倦怠感"
    ),
    ConversationItem(
        "Tôi có triệu chứng từ hôm qua",
        "昨日から症状があります",
        "医療",
        "病院・クリニック",
        "発症説明",
        "昨日から"
    ),
    ConversationItem(
        "Tôi bị như vậy ba ngày rồi",
        "3日前からこの状態です",
        "医療",
        "病院・クリニック",
        "発症説明",
        "期間"
    ),
    ConversationItem(
        "Hôm nay triệu chứng nặng hơn",
        "今日は症状が悪化しました",
        "医療",
        "病院・クリニック",
        "発症説明",
        "悪化"
    ),
    ConversationItem(
        "Tôi bị dị ứng thuốc này",
        "この薬にアレルギーがあります",
        "医療",
        "病院・クリニック",
        "アレルギー",
        "薬"
    ),
    ConversationItem(
        "Tôi bị dị ứng hải sản",
        "私はシーフードアレルギーです",
        "医療",
        "病院・クリニック",
        "アレルギー",
        "食べ物"
    ),
    ConversationItem(
        "Tôi không có dị ứng",
        "アレルギーはありません",
        "医療",
        "病院・クリニック",
        "アレルギー",
        "なし"
    ),
    ConversationItem(
        "Tôi cần xét nghiệm",
        "検査を受ける必要があります",
        "医療",
        "病院・クリニック",
        "検査",
        "検査"
    ),
    ConversationItem(
        "Tôi cần xét nghiệm máu",
        "血液検査が必要です",
        "医療",
        "病院・クリニック",
        "検査",
        "血液検査"
    ),
    ConversationItem(
        "Kết quả khi nào có?",
        "結果はいつ出ますか？",
        "医療",
        "病院・クリニック",
        "検査",
        "結果"
    ),
    ConversationItem(
        "Tôi cần đơn thuốc",
        "処方箋が必要です",
        "医療",
        "病院・クリニック",
        "薬",
        "処方箋"
    ),
    ConversationItem(
        "Thuốc này uống như thế nào?",
        "この薬はどう飲みますか？",
        "医療",
        "病院・クリニック",
        "薬",
        "服用方法"
    ),
    ConversationItem(
        "Thuốc này có tác dụng phụ không?",
        "この薬に副作用はありますか？",
        "医療",
        "病院・クリニック",
        "薬",
        "副作用"
    ),
    ConversationItem(
        "Tôi có bảo hiểm",
        "保険があります",
        "医療",
        "病院・クリニック",
        "保険",
        "保険"
    ),
    ConversationItem(
        "Bảo hiểm này có sử dụng được không?",
        "この保険は使えますか？",
        "医療",
        "病院・クリニック",
        "保険",
        "利用可否"
    ),
    ConversationItem(
        "Tôi cần giấy tờ cho bảo hiểm",
        "保険用の書類が必要です",
        "医療",
        "病院・クリニック",
        "保険",
        "書類"
    ),
    ConversationItem(
        "Chi phí khám bệnh là bao nhiêu?",
        "診察料はいくらですか？",
        "医療",
        "病院・クリニック",
        "会計",
        "診察料"
    ),
    ConversationItem(
        "Tôi trả bằng thẻ",
        "カードで支払います",
        "医療",
        "病院・クリニック",
        "会計",
        "カード"
    ),
    ConversationItem(
        "Tôi trả bằng tiền mặt",
        "現金で支払います",
        "医療",
        "病院・クリニック",
        "会計",
        "現金"
    ),
    ConversationItem(
        "Tôi cần đặt lịch tái khám",
        "再診の予約をしたいです",
        "医療",
        "病院・クリニック",
        "再診",
        "再診"
    ),
    ConversationItem(
        "Khi nào tôi nên quay lại?",
        "いつ再診すればよいですか？",
        "医療",
        "病院・クリニック",
        "再診",
        "再診時期"
    ),
    ConversationItem(
        "Cảm ơn bác sĩ",
        "先生、ありがとうございました",
        "医療",
        "病院・クリニック",
        "再診",
        "お礼"
    ),

    //
    // ✅ Part40 ジム・運動
    //
    ConversationItem(
        "Tôi muốn đăng ký tập gym",
        "ジムに入会したいです",
        "娯楽",
        "ジム・運動",
        "入会",
        "入会"
    ),
    ConversationItem(
        "Phí hội viên là bao nhiêu?",
        "会費はいくらですか？",
        "娯楽",
        "ジム・運動",
        "入会",
        "料金"
    ),
    ConversationItem(
        "Tôi muốn tham quan phòng gym",
        "ジムを見学したいです",
        "娯楽",
        "ジム・運動",
        "入会",
        "見学"
    ),
    ConversationItem(
        "Phòng gym mở cửa lúc mấy giờ?",
        "ジムは何時から営業していますか？",
        "娯楽",
        "ジム・運動",
        "施設確認",
        "営業時間"
    ),
    ConversationItem(
        "Phòng thay đồ ở đâu?",
        "更衣室はどこですか？",
        "娯楽",
        "ジム・運動",
        "施設確認",
        "更衣室"
    ),
    ConversationItem(
        "Có phòng tắm không?",
        "シャワールームはありますか？",
        "娯楽",
        "ジム・運動",
        "施設確認",
        "シャワー"
    ),
    ConversationItem(
        "Tôi muốn tập ngực",
        "胸を鍛えたいです",
        "娯楽",
        "ジム・運動",
        "トレーニング",
        "胸"
    ),
    ConversationItem(
        "Hôm nay tôi tập chân",
        "今日は脚を鍛えます",
        "娯楽",
        "ジム・運動",
        "トレーニング",
        "脚"
    ),
    ConversationItem(
        "Tôi đang tập lưng",
        "背中を鍛えています",
        "娯楽",
        "ジム・運動",
        "トレーニング",
        "背中"
    ),
    ConversationItem(
        "Máy này sử dụng như thế nào?",
        "このマシンはどう使いますか？",
        "娯楽",
        "ジム・運動",
        "マシン",
        "使い方"
    ),
    ConversationItem(
        "Máy này có đang được sử dụng không?",
        "このマシンは使用中ですか？",
        "娯楽",
        "ジム・運動",
        "マシン",
        "使用確認"
    ),
    ConversationItem(
        "Tôi có thể dùng máy này không?",
        "このマシンを使ってもいいですか？",
        "娯楽",
        "ジム・運動",
        "マシン",
        "利用許可"
    ),
    ConversationItem(
        "Tôi muốn giảm cân",
        "体重を減らしたいです",
        "娯楽",
        "ジム・運動",
        "目標",
        "ダイエット"
    ),
    ConversationItem(
        "Tôi muốn tăng cơ",
        "筋肉を付けたいです",
        "娯楽",
        "ジム・運動",
        "目標",
        "筋力向上"
    ),
    ConversationItem(
        "Tôi muốn khỏe hơn",
        "もっと健康になりたいです",
        "娯楽",
        "ジム・運動",
        "目標",
        "健康"
    ),
    ConversationItem(
        "Tôi tập gym ba lần một tuần",
        "週に3回ジムへ行きます",
        "娯楽",
        "ジム・運動",
        "運動習慣",
        "頻度"
    ),
    ConversationItem(
        "Tôi thường tập sau giờ làm",
        "仕事の後によく運動します",
        "娯楽",
        "ジム・運動",
        "運動習慣",
        "習慣"
    ),
    ConversationItem(
        "Tôi tập vào cuối tuần",
        "週末に運動します",
        "娯楽",
        "ジム・運動",
        "運動習慣",
        "週末"
    ),
    ConversationItem(
        "Tôi mệt rồi",
        "少し疲れました",
        "娯楽",
        "ジム・運動",
        "体調",
        "疲労"
    ),
    ConversationItem(
        "Tôi cần nghỉ một chút",
        "少し休憩します",
        "娯楽",
        "ジム・運動",
        "体調",
        "休憩"
    ),
    ConversationItem(
        "Tôi cần uống nước",
        "水を飲みたいです",
        "娯楽",
        "ジム・運動",
        "体調",
        "水分補給"
    ),
    ConversationItem(
        "Bạn tập gym bao lâu rồi?",
        "どのくらいジムに通っていますか？",
        "娯楽",
        "ジム・運動",
        "雑談",
        "経験"
    ),
    ConversationItem(
        "Bạn thường tập môn gì?",
        "普段どんな運動をしていますか？",
        "娯楽",
        "ジム・運動",
        "雑談",
        "運動"
    ),
    ConversationItem(
        "Chúng ta tập cùng nhau nhé",
        "一緒にトレーニングしましょう",
        "娯楽",
        "ジム・運動",
        "雑談",
        "誘い"
    ),
    ConversationItem(
        "Buổi tập hôm nay rất tốt",
        "今日のトレーニングは良かったです",
        "娯楽",
        "ジム・運動",
        "感想",
        "満足"
    ),
    ConversationItem(
        "Tôi cảm thấy khỏe hơn",
        "体の調子が良くなりました",
        "娯楽",
        "ジム・運動",
        "感想",
        "効果"
    ),
    ConversationItem(
        "Tôi sẽ quay lại ngày mai",
        "また明日来ます",
        "娯楽",
        "ジム・運動",
        "感想",
        "継続"
    )
)
