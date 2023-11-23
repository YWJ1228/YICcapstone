import { PageConfig } from "./Config";

/**
 * Base url : localhost
 * Api url : 로그인 인증이 필요한 api
 * Admin url : 관리자만 사용하는 api
 */
const BASE_URL = "http://localhost:8080";
const API_URL = `${BASE_URL}/api`;
const ADMIN_URL = `${BASE_URL}/api/admin`;

// base url
const EBOOK_URL = `${BASE_URL}/ebook`;
const VOICE_URL = `${BASE_URL}/voice-model`;
const REVIEW_URL = `${BASE_URL}/review`;

// api url
const EBOOK_API_URL = `${API_URL}/ebook`;
const VOICE_API_URL = `${API_URL}/voice-model`;
const PURCHASE_API_URL = `${API_URL}/purchase`;
const REVIEW_API_URL = `${API_URL}/review`;
const WISH_API_URL = `${API_URL}/wish`;

// admin url
const EBOOK_ADMIN_URL = `${ADMIN_URL}/ebook`;
const VOICE_ADMIN_URL = `${ADMIN_URL}/voice-model`;



export const API = {
    // #########  우준 API  ########
    // GET
    VERIFY_EMAIL:           `${BASE_URL}/api/sign-up/username`,
    CHECK_NICKNAME:         `${BASE_URL}/api/sign-up/nickname`,
    USER_INFO :             `${BASE_URL}/api/user/info`,

    // POST
    REGISTER_USER:          `${BASE_URL}/api/sign-up`,
    LOGIN_USER:             `${BASE_URL}/api/log-in`,
    FIND_ID:                `${BASE_URL}/api/find/id`,
    FIND_PWD:               `${BASE_URL}/api/find/password`,

    // DELETE
    DELETE_USER:            `${BASE_URL}/api/user`,

    // PATCH
    CHANGE_PWD:             `${BASE_URL}/api/user/password`,
    CHANGE_NICKNAME:        `${BASE_URL}/api/user/nickname`,

    SEARCH_EBOOK_NAME :          `${EBOOK_URL}/search/name?keyword=`,
    SEARCH_EBOOK_AUTHOR :        `${EBOOK_URL}/search/author?keyword=`,
    SEARCH_EBOOK_PUBLISHER :     `${EBOOK_URL}/search/publisher?keyword=`,
    SEARCH_VOICE :               `${VOICE_URL}/search/name?keyword=`,

    // 피드백 api
    CREATE_FEEDBACK :           `${API_URL}/user/feedback`,
    READ_FEEDBACK :             `${ADMIN_URL}/feedback?page=0&size=10`,
    DELETE_FEEDBACK:            `${ADMIN_URL}/feedback`,

    // 장바구니 api
    READ_EBOOK_CART :                 `${API_URL}/user/basket/e-book`,
    READ_VOICE_CART :                 `${API_URL}/user/basket/voice-model`,
    ADD_EBOOKITEM_CART :              `${API_URL}/user/basket/e-book`,
    ADD_VOICEITEM_CART :              `${API_URL}/user/basket/voice-model`,
    DELETE_EBOOKITEM_CART :           `${API_URL}/user/basket/e-book`,
    DELETE_VOICEITEM_CART :           `${API_URL}/user/basket/voice-model`,

    // 오디오 북 api
    LOAD_AUDIOBOOK_PATH :               `${API_URL}/user/audio-book/play`,




    // #########  태훈 API  #############
    // EBOOK
    // POST
    UPLOAD_EBOOK:                       `${EBOOK_ADMIN_URL}`,

    // PUT
    UPDATE_EBOOK:                       `${EBOOK_ADMIN_URL}/{ebookId}`,

    // DELETE
    DELETE_EBOOK:                       `${EBOOK_ADMIN_URL}/{ebookId}`,

    // GET
    LOAD_EBOOK:                         `${EBOOK_URL}`,
    LOAD_BANNER_EBOOKS:                 `${EBOOK_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`,            //배너
    LOAD_RECENT_EBOOKS:                 `${EBOOK_URL}/list?size=${PageConfig.NUM_RECENT_EBOOKS}&page=0`, // 최신 업로드
    LOAD_POPULAR_EBOOKS:                `${EBOOK_URL}/list/popularity?size=${PageConfig.NUM_POPULAR_EBOOKS}&page=0`, // 인기 리스트

    LOAD_EBOOK_SORTBY_UPLOAD :          `${EBOOK_URL}/list?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=`,
    LOAD_EBOOK_SORTBY_POPULARITY :      `${EBOOK_URL}/list/popularity?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=`,
    LOAD_EBOOK_SORTBY_LOW_PRICE :       `${EBOOK_URL}/list/price/asc?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=`,
    LOAD_EBOOK_SORTBY_HIGH_PRICE :      `${EBOOK_URL}/list/price/desc?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=`,


    LOAD_CATEGORY_EBOOKS_UPLOAD:                        `${EBOOK_URL}/list/category?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&category=`,
    LOAD_CATEGORY_EBOOKS_SORTBY_POPULARITY :     `${EBOOK_URL}/list/category/popularity?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&category=`,
    LOAD_CATEGORY_EBOOKS_SORTBY_LOW_PRICE :      `${EBOOK_URL}/list/category/price/asc?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&category=`,
    LOAD_CATEGORY_EBOOKS_SORTBY_HIGH_PRICE :     `${EBOOK_URL}/list/category/price/desc?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&category=`,


    // VOICE
    // POST
    UPLOAD_VOICE:                       `${VOICE_ADMIN_URL}`,
    PREFERENCE_VOICE:                   `${VOICE_API_URL}/{voiceModelId}/preference`,
    PREFERENCE_VERIFY_VOICE:            `${VOICE_API_URL}/{voiceModelId}/preference/verify`,

    // PUT
    UPDATE_VOICE:                       `${VOICE_ADMIN_URL}/{voiceModelId}`,

    // DELETE
    DELETE_VOICE:                       `${VOICE_ADMIN_URL}/{voiceModelId}`,

    // GET
    LOAD_VOICE:                         `${VOICE_URL}`,
    LOAD_BANNER_VOICES:                 `${VOICE_URL}/list?size=${PageConfig.NUM_BANNER_VOICES}&page=0`,   // 배너
    LOAD_RECENT_VOICES:                 `${VOICE_URL}/list?size=${PageConfig.NUM_RECENT_VOICES}&page=0`,  // 최신 업로드
    LOAD_POPULAR_VOICES:                `${VOICE_URL}/list/popularity?size=${PageConfig.NUM_POPULAR_VOICES}&page=0`, // 인기 리스트
    LOAD_CATEGORY_VOICES:               `${VOICE_URL}/list/category?category=`,

    LOAD_VOICE_SORTBY_UPLOAD :          `${VOICE_URL}/list?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,
    LOAD_VOICE_SORTBY_POPULARITY :      `${VOICE_URL}/list/popularity?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,
    LOAD_VOICE_SORTBY_LOW_PRICE :       `${VOICE_URL}/list/price/asc?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,
    LOAD_VOICE_SORTBY_HIGH_PRICE :      `${VOICE_URL}/list/price/desc?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,


    LOAD_CATEGORY_VOICE_UPLOAD:               `${VOICE_URL}/list/category?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&category=`,
    LOAD_CATEGORY_VOICE_SORTBY_POPULARITY :     `${VOICE_URL}/list/category/popularity?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&category=`,
    LOAD_CATEGORY_VOICE_SORTBY_LOW_PRICE :      `${VOICE_URL}/list/category/price/asc?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&category=`,
    LOAD_CATEGORY_VOICE_SORTBY_HIGH_PRICE :     `${VOICE_URL}/list/category/price/desc?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&category=`,

    // 내 서재 API
    // 더미 API
    LOAD_MYPAGE_BOOKLIST :              `${EBOOK_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`,
    LOAD_MYPAGE_VOICELIST :             `${VOICE_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`,
    LOAD_MYPAGE_REVIEWLIST :            `${EBOOK_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`, // 지웠음
    LOAD_MYPAGE_LIKELIST :              `${EBOOK_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`,

    //Admin API
    ADMIN_ADD_VOICE :                   `${VOICE_ADMIN_URL}`,
    ADMIN_LOAD_VOICELIST :              `${VOICE_URL}/list?&page=0&size=`, // for totalPages
    ADMIN_LOAD_VOICELIST_SIZE :         `${VOICE_URL}/list?size=1&page=0`,
    ADMIN_ADD_EBOOK:                    `${EBOOK_URL}`,
    ADMIN_LOAD_EBOOKLIST:               `${EBOOK_URL}/list?page=0&size=`,
    ADMIN_LOAD_EBOOKLIST_SIZE:          `${EBOOK_URL}/list?size=1&page=0`,
    ADMIN_ADD_USER:                     ``,
    ADMIN_LOAD_USERLIST :               ``,


    
    // Purchase
    // POST
    PURCHASE_EBOOK:                     `${PURCHASE_API_URL}/ebook`,
    PURCHASE_VOICE:                     `${PURCHASE_API_URL}/voice-model`,
    // GET
    LOAD_PURCHASED_EBOOKS:              `${PURCHASE_API_URL}/ebook?page=0&size=`,
    LOAD_PURCHASED_EBOOKS_SIZE:         `${PURCHASE_API_URL}/ebook?page=0&size=1`,
    LOAD_PURCHASED_VOICES:              `${PURCHASE_API_URL}/voice-model?page=0&size=`,
    LOAD_PURCHASED_VOICES_SIZE:         `${PURCHASE_API_URL}/voice-model?page=0&size=1`,

    NUM_PAGES_PURCHASED_EBOOKS:         `${PURCHASE_API_URL}/ebook/total?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}`,
    NUM_PAGES_PURCHASED_VOICES:         `${PURCHASE_API_URL}/voice-model/total?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}`,

    // Review
    // POST
    REVIEW_EBOOK:                      `${REVIEW_API_URL}/ebook`,
    REVIEW_VOICE:                      `${REVIEW_API_URL}/voice-model`,


    // PUT
    UPDATE_REVIEW_EBOOK:               `${REVIEW_API_URL}/ebook`,
    UPDATE_REVIEW_VOICE:               `${REVIEW_API_URL}/voice-model`,

    // GET
    LOAD_REVIEW_EBOOKS:              `${REVIEW_URL}/ebook?size=${PageConfig.NUM_REVIEWS_FOR_EBOOK}&ebookId=`,
    LOAD_REVIEW_VOICES:              `${REVIEW_URL}/voice-model?size=${PageConfig.NUM_REVIEWS_FOR_VOICE}&voiceModelId=`,
    LOAD_REVIEW_WRITTEN_EBOOKS:      `${REVIEW_API_URL}/ebook/written?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=`,
    LOAD_REVIEW_WRITTEN_VOICES:      `${REVIEW_API_URL}/voice-model/written?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,
    LOAD_REVIEW_NOT_WRITTEN_EBOOKS:  `${REVIEW_API_URL}/ebook/not-written?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=0`,
    LOAD_REVIEW_NOT_WRITTEN_VOICES:  `${REVIEW_API_URL}/voice-model/not-written?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,

    // DELETE
    DELETE_REVIEW_EBOOK:              `${REVIEW_API_URL}/ebook?purchaseId=`,
    DELETE_REVIEW_VOICE:              `${REVIEW_API_URL}/voice-model?purchaseId=`,

    // Wish
    // POST
    WISH_EBOOK:                      `${WISH_API_URL}/ebook?ebookId=`,
    WISH_VOICE:                      `${WISH_API_URL}/voice-model?voiceModelId=`,
    WISH_VERIFY_EBOOK:              `${WISH_API_URL}/ebook`,
    WISH_VERIFY_VOICE:              `${WISH_API_URL}/voice-model`,

    // GET
    LOAD_WISH_EBOOKS:                   `${WISH_API_URL}/ebook?page=0&size=`,
    LOAD_WISH_EBOOKS_SIZE:              `${WISH_API_URL}/ebook?size=1&page=0`,
    LOAD_WISH_VOICES:                   `${WISH_API_URL}/voice-model?page=0&size=`,
    LOAD_WISH_VOICES_SIZE:                   `${WISH_API_URL}/voice-model?size=1&page=0`,

}