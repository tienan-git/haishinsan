package jp.acepro.haishinsan.enums;

/**
 * オペレーション 列挙クラス。
 *
 *
 */
public enum Operation implements CodeEnum<String> {

	USER_LIST("USER_LIST", "ユーザー一覧"), // ユーザー一覧
	USER_VIEW("USER_VIEW", "ユーザー照会"), // ユーザー照会
	USER_CREATE("USER_CREATE", "ユーザー作成"), // ユーザー作成
	USER_UPDATE("USER_UPDATE", "ユーザー変更"), // ユーザー変更
	USER_DELETE("USER_DELETE", "ユーザー削除"), // ユーザー削除
	LOGIN("LOGIN", "ログイン"), // ログイン
	LOGOUT("LOGOUT", "ログアウト"), // ログアウト

	ISSUE_CREATE("ISSUE_CREATE", "案件新規作成"), // 案件新規作成
	ISSUE_LIST("ISSUE_LIST", "案件一覧"), // 案件一覧

	GOOGLE_CAMPAIGN_CREATE("GOOGLE_CAMPAIGN_CREATE", "GOOGLEキャンペーン新規作成"), // GOOGLEキャンペーン新規作成
	GOOGLE_CAMPAIGN_STATUS_UPDATE("GOOGLE_CAMPAIGN_STATUS_UPDATE", "GOOGLEキャンペーンステータス更新"), // GOOGLEキャンペーンステータス更新
	GOOGLE_CAMPAIGN_DELETE("GOOGLE_CAMPAIGN_DELETE", "GOOGLEキャンペーン削除"), // GOOGLEキャンペーン新規作成
	GOOGLE_CAMPAIGN_LIST("GOOGLE_CAMPAIGN_LIST", "GOOGLEキャンペーン一覧"), // GOOGLEキャンペーン一覧
	GOOGLE_CAMPAIGN_VIEW("GOOGLE_CAMPAIGN_VIEW", "GOOGLEキャンペーン照会"), // GOOGLEキャンペーン照会

	GOOGLE_ISSUE_CREATE("GOOGLE_ISSUE_CREATE", "GOOGLE案件新規作成"), // GOOGLE案件新規作成
	GOOGLE_ISSUE_STATUS_UPDATE("GOOGLE_ISSUE_STATUS_UPDATE", "GOOGLE案件ステータス更新"), // GOOGLE案件ステータス更新
	GOOGLE_ISSUE_DELETE("GOOGLE_ISSUE_DELETE", "GOOGLE案件削除"), // GOOGLE案件新規作成
	GOOGLE_ISSUE_LIST("GOOGLE_ISSUE_LIST", "GOOGLE案件一覧"), // GOOGLE案件一覧
	GOOGLE_ISSUE_VIEW("GOOGLE_ISSUE_VIEW", "GOOGLE案件照会"), // GOOGLE案件照会

	GOOGLE_REPORT_VIEW("GOOGLE_REPORT_VIEW", "GOOGLEレポート照会"), // GOOGLEレポート照会
	GOOGLE_DEVICE_REPORT_VIEW("GOOGLE_DEVICE_REPORT_VIEW", "GOOGLEデバイス別レポート照会"), // GOOGLEデバイス別レポート照会
	GOOGLE_REGION_REPORT_VIEW("GOOGLE_REGION_REPORT_VIEW", "GOOGLE地域別レポート照会"), // GOOGLE地域別レポート照会
	GOOGLE_DATE_REPORT_VIEW("GOOGLE_DATE_REPORT_VIEW", "GOOGLE日別レポート照会"), // GOOGLE日別レポート照会
	GOOGLE_DEVICE_REPORT_DOWNLOAD("GOOGLE_DEVICE_REPORT_DOWNLOAD", "GOOGLEデバイス別レポートダウンロード"), // GOOGLEデバイス別レポート照会
	GOOGLE_REGION_REPORT_DOWNLOAD("GOOGLE_REGION_REPORT_DOWNLOAD", "GOOGLE地域別レポートダウンロード"), // GOOGLE地域別レポート照会
	GOOGLE_DATE_REPORT_DOWNLOAD("GOOGLE_DATE_REPORT_DOWNLOAD", "GOOGLE日別レポートダウンロード"), // GOOGLE日別レポート照会

	GOOGLE_TEMPLATE_CREATE("GOOGLE_TEMPLATE_CREATE", "GOOGLEテンプレート新規作成"), // GOOGLEキャンペーン新規作成
	GOOGLE_TEMPLATE_UPDATE("GOOGLE_TEMPLATE_UPDATE", "GOOGLEテンプレート更新"), // GOOGLEキャンペーンステータス更新
	GOOGLE_TEMPLATE_DELETE("GOOGLE_TEMPLATE_DELETE", "GOOGLEテンプレート削除"), // GOOGLEキャンペーン新規作成
	GOOGLE_TEMPLATE_LIST("GOOGLE_TEMPLATE_LIST", "GOOGLEテンプレート一覧"), // GOOGLEキャンペーン一覧
	GOOGLE_TEMPLATE_VIEW("GOOGLE_TEMPLATE_VIEW", "GOOGLEテンプレート照会"), // GOOGLEキャンペーン照会

	FACEBOOK_CAMPAIGN_CREATE("FACEBOOK_CAMPAIGN_CREATE", "FACEBOOKキャンペーン新規作成"), // FACEBOOKキャンペーン新規作成
	FACEBOOK_CAMPAIGN_STATUS_UPDATE("FACEBOOK_CAMPAIGN_STATUS_UPDATE", "FACEBOOKキャンペーンステータス更新"), // FACEBOOKキャンペーンステータス更新
	FACEBOOK_CAMPAIGN_DELETE("FACEBOOK_CAMPAIGN_DELETE", "FACEBOOKキャンペーン削除"), // FACEBOOKキャンペーン新規作成
	FACEBOOK_CAMPAIGN_LIST("FACEBOOK_CAMPAIGN_LIST", "FACEBOOKキャンペーン一覧"), // FACEBOOKキャンペーン一覧
	FACEBOOK_CAMPAIGN_VIEW("FACEBOOK_CAMPAIGN_VIEW", "FACEBOOKキャンペーン照会"), // FACEBOOKキャンペーン照会

	FACEBOOK_ISSUE_CREATE("FACEBOOK_ISSUE_CREATE", "FACEBOOK案件新規作成"), // FACEBOOK案件新規作成
	FACEBOOK_ISSUE_STATUS_UPDATE("FACEBOOK_ISSUE_STATUS_UPDATE", "FACEBOOK案件ステータス更新"), // FACEBOOK案件ステータス更新
	FACEBOOK_ISSUE_DELETE("FACEBOOK_ISSUE_DELETE", "FACEBOOK案件削除"), // FACEBOOK案件新規作成
	FACEBOOK_ISSUE_LIST("FACEBOOK_ISSUE_LIST", "FACEBOOK案件一覧"), // FACEBOOK案件一覧
	FACEBOOK_ISSUE_VIEW("FACEBOOK_ISSUE_VIEW", "FACEBOOK案件照会"), // FACEBOOK案件照会

	FACEBOOK_REPORT_VIEW("FACEBOOK_REPORT_VIEW", "FACEBOOKレポート照会"), // FACEBOOKレポート照会
	FACEBOOK_DEVICE_REPORT_VIEW("FACEBOOK_DEVICE_REPORT_VIEW", "FACEBOOKデバイス別レポート照会"), // FACEBOOKデバイス別レポート照会
	FACEBOOK_REGION_REPORT_VIEW("FACEBOOK_REGION_REPORT_VIEW", "FACEBOOK地域別レポート照会"), // FACEBOOK地域別レポート照会
	FACEBOOK_DATE_REPORT_VIEW("FACEBOOK_DATE_REPORT_VIEW", "FACEBOOK日別レポート照会"), // FACEBOOK日別レポート照会
	FACEBOOK_DEVICE_REPORT_DOWNLOAD("FACEBOOK_DEVICE_REPORT_DOWNLOAD", "FACEBOOKデバイス別レポートダウンロード"), // FACEBOOKデバイス別レポート照会
	FACEBOOK_REGION_REPORT_DOWNLOAD("FACEBOOK_REGION_REPORT_DOWNLOAD", "FACEBOOK地域別レポートダウンロード"), // FACEBOOK地域別レポート照会
	FACEBOOK_DATE_REPORT_DOWNLOAD("FACEBOOK_DATE_REPORT_DOWNLOAD", "FACEBOOK日別レポートダウンロード"), // FACEBOOK日別レポート照会

	FACEBOOK_TEMPLATE_CREATE("FACEBOOK_TEMPLATE_CREATE", "FACEBOOKテンプレート新規作成"), // FACEBOOKキャンペーン新規作成
	FACEBOOK_TEMPLATE_UPDATE("FACEBOOK_TEMPLATE_UPDATE", "FACEBOOKテンプレート更新"), // FACEBOOKキャンペーンステータス更新
	FACEBOOK_TEMPLATE_DELETE("FACEBOOK_TEMPLATE_DELETE", "FACEBOOKテンプレート削除"), // FACEBOOKキャンペーン新規作成
	FACEBOOK_TEMPLATE_LIST("FACEBOOK_TEMPLATE_LIST", "FACEBOOKテンプレート一覧"), // FACEBOOKキャンペーン一覧
	FACEBOOK_TEMPLATE_VIEW("FACEBOOK_TEMPLATE_VIEW", "FACEBOOKテンプレート照会"), // FACEBOOKキャンペーン照会

	TWITTER_CAMPAIGN_CREATE("TWITTER_CAMPAIGN_CREATE", "TWITTERキャンペーン新規作成"), // TWITTERキャンペーン新規作成
	TWITTER_CAMPAIGN_STATUS_UPDATE("TWITTER_CAMPAIGN_STATUS_UPDATE", "TWITTERキャンペーンステータス更新"), // TWITTERキャンペーンステータス更新
	TWITTER_CAMPAIGN_DELETE("TWITTER_CAMPAIGN_DELETE", "TWITTERキャンペーン削除"), // TWITTERキャンペーン新規作成
	TWITTER_CAMPAIGN_LIST("TWITTER_CAMPAIGN_LIST", "TWITTERキャンペーン一覧"), // TWITTERキャンペーン一覧
	TWITTER_CAMPAIGN_VIEW("TWITTER_CAMPAIGN_VIEW", "TWITTERキャンペーン照会"), // TWITTERキャンペーン照会

	TWITTER_REPORT_VIEW("TWITTER_REPORT_VIEW", "TWITTERレポート照会"), // TWITTERレポート照会
	TWITTER_DEVICE_REPORT_VIEW("TWITTER_DEVICE_REPORT_VIEW", "TWITTERデバイス別レポート照会"), // TWITTERデバイス別レポート照会
	TWITTER_REGION_REPORT_VIEW("TWITTER_REGION_REPORT_VIEW", "TWITTER地域別レポート照会"), // TWITTER地域別レポート照会
	TWITTER_DATE_REPORT_VIEW("TWITTER_DATE_REPORT_VIEW", "TWITTER日別レポート照会"), // TWITTER日別レポート照会
	TWITTER_DEVICE_REPORT_DOWNLOAD("TWITTER_DEVICE_REPORT_DOWNLOAD", "TWITTERデバイス別レポートダウンロード"), // TWITTERデバイス別レポート照会
	TWITTER_REGION_REPORT_DOWNLOAD("TWITTER_REGION_REPORT_DOWNLOAD", "TWITTER地域別レポートダウンロード"), // TWITTER地域別レポート照会
	TWITTER_DATE_REPORT_DOWNLOAD("TWITTER_DATE_REPORT_DOWNLOAD", "TWITTER日別レポートダウンロード"), // TWITTER日別レポート照会

	TWITTER_TEMPLATE_CREATE("TWITTER_TEMPLATE_CREATE", "TWITTERテンプレート新規作成"), // TWITTERキャンペーン新規作成
	TWITTER_TEMPLATE_UPDATE("TWITTER_TEMPLATE_UPDATE", "TWITTERテンプレート更新"), // TWITTERキャンペーンステータス更新
	TWITTER_TEMPLATE_DELETE("TWITTER_TEMPLATE_DELETE", "TWITTERテンプレート削除"), // TWITTERキャンペーン新規作成
	TWITTER_TEMPLATE_LIST("TWITTER_TEMPLATE_LIST", "TWITTERテンプレート一覧"), // TWITTERキャンペーン一覧
	TWITTER_TEMPLATE_VIEW("TWITTER_TEMPLATE_VIEW", "TWITTERテンプレート照会"), // TWITTERキャンペーン照会

	YOUTUBE_REPORT_VIEW("YOUTUBE_REPORT_VIEW", "YOUTUBEレポート照会"), // YOUTUBEレポート照会
	YOUTUBE_DEVICE_REPORT_VIEW("YOUTUBE_DEVICE_REPORT_VIEW", "YOUTUBEデバイス別レポート照会"), // YOUTUBEデバイス別レポート照会
	YOUTUBE_REGION_REPORT_VIEW("YOUTUBE_REGION_REPORT_VIEW", "YOUTUBE地域別レポート照会"), // YOUTUBE地域別レポート照会
	YOUTUBE_DATE_REPORT_VIEW("YOUTUBE_DATE_REPORT_VIEW", "YOUTUBE日別レポート照会"), // YOUTUBE日別レポート照会
	YOUTUBE_DEVICE_REPORT_DOWNLOAD("YOUTUBE_DEVICE_REPORT_DOWNLOAD", "YOUTUBEデバイス別レポートダウンロード"), // YOUTUBEデバイス別レポート照会
	YOUTUBE_REGION_REPORT_DOWNLOAD("YOUTUBE_REGION_REPORT_DOWNLOAD", "YOUTUBE地域別レポートダウンロード"), // YOUTUBE地域別レポート照会
	YOUTUBE_DATE_REPORT_DOWNLOAD("YOUTUBE_DATE_REPORT_DOWNLOAD", "YOUTUBE日別レポートダウンロード"), // YOUTUBE日別レポート照会

	DSP_TEMPLATE_CREATE("DSP_TEMPLATE_CREATE", "DSPテンプレート新規作成"), // DSPテンプレート新規作成
	DSP_TEMPLATE_UPDATE("DSP_TEMPLATE_UPDATE", "DSPテンプレート更新"), // DSPキャンペーンステータス更新
	DSP_TEMPLATE_DELETE("DSP_TEMPLATE_DELETE", "DSPテンプレート削除"), // DSPキャンペーン新規作成
	DSP_TEMPLATE_LIST("DSP_TEMPLATE_LIST", "DSPテンプレート一覧"), // DSPキャンペーン一覧
	DSP_TEMPLATE_VIEW("DSP_TEMPLATE_VIEW", "DSPテンプレート照会"), // DSPキャンペーン照会

	DSP_SEGMENT_CREATE("DSP_SEGMENT_CREATE", "DSPセグメント新規作成"), // DSPセグメント新規作成
	DSP_SEGMENT_DELETE("DSP_SEGMENT_DELETE", "DSPセグメント削除"), // DSPセグメント削除
	DSP_SEGMENT_LIST("DSP_SEGMENT_LIST", "DSPセグメント一覧"), // DSPセグメント一覧

	DSP_CREATIVE_CREATE("DSP_CREATIVE_CREATE", "DSPクリエイティブ新規作成"), // DSPクリエイティブ新規作成
	DSP_CREATIVE_DELETE("DSP_CREATIVE_DELETE", "DSPクリエイティブ削除"), // DSPクリエイティブ削除
	DSP_CREATIVE_LIST("DSP_CREATIVE_LIST", "DSPクリエイティブ一覧"), // DSPクリエイティブ一覧
	DSP_CREATIVE_VIEW("DSP_CREATIVE_VIEW", "DSPクリエイティブ照会"), // DSPクリエイティブ照会

	DSP_CAMPAIGN_CREATE("DSP_CAMPAIGN_CREATE", "DSPキャンペーン新規作成"), // DSPキャンペーン新規作成
	DSP_CAMPAIGN_UPDATE("DSP_CAMPAIGN_UPDATE", "DSPキャンペーン削除"), // DSPキャンペーン削除
	DSP_CAMPAIGN_LIST("DSP_CAMPAIGN_LIST", "DSPキャンペーン一覧"), // DSPキャンペーン一覧
	DSP_CAMPAIGN_VIEW("DSP_CAMPAIGN_VIEW", "DSPキャンペーン照会"), // DSPキャンペーン照会
	DSP_CAMPAIGN_DELETE("DSP_CAMPAIGN_DELETE", "DSPキャンペーン削除"), // DSPキャンペーン削除

	DSP_SEGMENT_REPORT_VIEW("DSP_SEGMENT_REPORT_VIEW", "DSPセグメントレポート照会"), // DSPセグメントレポート照会
	DSP_REPORT_VIEW("DSP_REPORT_VIEW", "DSPレポート照会"), // DSPレポート照会
	DSP_SEGMENT_REPORT_DOWNLOAD("DSP_SEGMENT_REPORT_DOWNLOAD", "DSPセグメントレポートダウンロード"), // DSPセグメントレポートダウンロード
	DSP_REPORT_DOWNLOAD("DSP_REPORT_DOWNLOAD", "DSPレポートダウンロード"), // DSPレポートダウンロード

	YAHOO_CAMPAIGN_VIEW("YAHOO_CAMPAIGN_VIEW", "YAHOO案件依頼の照会"), // YAHOO案件依頼の照会
	YAHOO_CAMPAIGN_REQUEST("YAHOO_CAMPAIGN_REQUEST", "YAHOO案件依頼の作成"), // YAHOO案件依頼の作成
	YAHOO_CAMPAIGN_UPDATE("YAHOO_CAMPAIGN_UPDATE", "YAHOO案件依頼の更新"), // YAHOO案件依頼の更新
	YAHOO_CAMPAIGN_DELETE("YAHOO_CAMPAIGN_DELETE", "YAHOO案件依頼の削除"), // YAHOO案件依頼の削除
	YAHOO_CSV_UPLOAD("YAHOO_CSV_UPLOAD", "YAHOOキャンペインCSVのアップロード"), // YAHOOキャンペインCSVのアップロード

	YAHOO_DEVICE_REPORT_VIEW("YAHOO_DEVICE_REPORT_VIEW", "YAHOOデバイス別レポート照会"), // YAHOOデバイス別レポート照会
	YAHOO_REGION_REPORT_VIEW("YAHOO_REGION_REPORT_VIEW", "YAHOO地域別レポート照会"), // YAHOO地域別レポート照会
	YAHOO_DATE_REPORT_VIEW("YAHOO_DATE_REPORT_VIEW", "YAHOO日別レポート照会"), // YAHOO日別レポート照会
	YAHOO_DEVICE_REPORT_DOWNLOAD("YAHOO_DEVICE_REPORT_DOWNLOAD", "YAHOOデバイス別レポートダウンロード"), // YAHOOデバイス別レポートダウンロード
	YAHOO_REGION_REPORT_DOWNLOAD("YAHOO_REGION_REPORT_DOWNLOAD", "YAHOO地域別レポートダウンロード"), // YAHOO地域別レポートダウンロード
	YAHOO_DATE_REPORT_DOWNLOAD("YAHOO_DATE_REPORT_DOWNLOAD", "YAHOO日別レポートダウンロード"), // YAHOO日別レポートダウンロード

	GET_DSP_REPORT_RAWDATA("GET_DSP_REPORT_RAWDATA", "レポート生データ取得"), // DSPレポート生データ取得
	GET_GOOGLE_REPORT_RAWDATA("GET_GOOGLE_REPORT_RAWDATA", "レポート生データ取得"), // Googleレポート生データ取得
	GET_FACEBOOK_REPORT_RAWDATA("GET_FACEBOOK_REPORT_RAWDATA", "レポート生データ取得"), // Facebookレポート生データ取得
	GET_TWITTER_REPORT_RAWDATA("GET_TWITTER_REPORT_RAWDATA", "レポート生データ取得"), // Twitterレポート生データ取得
	GET_YOUTUBE_REPORT_RAWDATA("GET_YOUTUBE_REPORT_RAWDATA", "レポート生データ取得"), // Youtubeレポート生データ取得
	GET_SEGMENT_REPORT_RAWDATA("GET_SEGMENT_REPORT_RAWDATA", "レポート生データ取得"); // セグメントレポート生データ取得

	private Operation(String value, String label) {
		this.value = value;
		this.label = label;
	}

	/** 値 */
	private String value;

	/** 名称 */
	private String label;

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}

	public static Operation of(String code) {
		return CodeEnum.of(Operation.class, code);
	}
}
