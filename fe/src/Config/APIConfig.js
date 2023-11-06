import { PageConfig } from "./Config";

const BASE_URL = "http://localhost:8080";

const EBOOK_URL = `${BASE_URL}/ebook`;
const VOICE_URL = `${BASE_URL}/voice-model`;
const AUDIOBOOK_URL = `${BASE_URL}`;
const PURCHASE_URL = `${BASE_URL}/purchase`;
const REVIEW_URL = `${BASE_URL}/review`;
const WISH_URL = `${BASE_URL}/wish`;

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



    // #########  태훈 API  #############
    // EBOOK
    // POST
    UPLOAD_EBOOK:                       `${EBOOK_URL}`,

    // PUT
    UPDATE_EBOOK:                       `${EBOOK_URL}/{ebookId}`,

    // DELETE
    DELETE_EBOOK:                       `${EBOOK_URL}/{ebookId}`,

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


    NUM_PAGES_EBOOKLIST:                `${EBOOK_URL}/list/total?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}`,
    NUM_PAGES_CATEGORY_EBOOKLIST:       `${EBOOK_URL}/list/total/category?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&category=`,


    // VOICE
    // POST
    UPLOAD_VOICE:                       `${VOICE_URL}`,
    PREFERENCE_VOICE:                   `${VOICE_URL}/{voiceModelId}/preference`,
    PREFERENCE_VERIFY_VOICE:            `${VOICE_URL}/{voiceModelId}/preference/verify`,

    // PUT
    UPDATE_VOICE:                       `${VOICE_URL}/{voiceModelId}`,

    // DELETE
    DELETE_VOICE:                       `${VOICE_URL}/{voiceModelId}`,

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

    NUM_PAGES_VOICELIST:                `${VOICE_URL}/list/total?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}`,
    NUM_PAGES_CATEGORY_VOICELIST:       `${VOICE_URL}/list/total/category?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&category=`,


    // 내 서재 API
    // 더미 API
    LOAD_MYPAGE_BOOKLIST :              `${EBOOK_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`,
    LOAD_MYPAGE_VOICELIST :             `${VOICE_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`,
    LOAD_MYPAGE_REVIEWLIST :            `${EBOOK_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`,
    LOAD_MYPAGE_LIKELIST :              `${EBOOK_URL}/list?size=${PageConfig.NUM_BANNER_EBOOKS}&page=0`,

    //Admin API
    ADMIN_ADD_VOICE :                   `${VOICE_URL}`,
    ADMIN_LOAD_VOICELIST :              `${VOICE_URL}/list?size=${PageConfig.NUM_BANNER_VOICES}&page=0`, // DUMMY
    ADMIN_ADD_EBOOK:                    `${EBOOK_URL}`,
    ADMIN_LOAD_EBOOKLIST:              `${EBOOK_URL}/list?size=${PageConfig.NUM_BANNER_VOICES}&page=0`,
    ADMIN_ADD_USER:                     ``,
    ADMIN_LOAD_USERLIST :               ``,



    //DUMMY API
    PLAYER_LOAD_LAST_PLAYEDBOOK :       `${AUDIOBOOK_URL}`,
    PLAYER_LOAD_PLAYLIST :              `${AUDIOBOOK_URL}`,
    
    // Purchase
    // POST
    PURCHASE_EBOOK:                     `${PURCHASE_URL}/ebook`,
    PURCHASE_VOICE:                     `${PURCHASE_URL}/voice-model`,
    // GET
    LOAD_PURCHASED_EBOOKS:              `${PURCHASE_URL}/ebook?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=`,
    LOAD_PURCHASED_VOICES:              `${PURCHASE_URL}/voice-model?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,

    NUM_PAGES_PURCHASED_EBOOKS:         `${PURCHASE_URL}/ebook/total?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}`,
    NUM_PAGES_PURCHASED_VOICES:         `${PURCHASE_URL}/voice-model/total?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}`,

    // Review
    // POST
    REVIEW_EBOOK:                      `${REVIEW_URL}/ebook`,
    REVIEW_VOICE:                      `${REVIEW_URL}/voice-model`,

    // PUT
    UPDATE_REVIEW_EBOOK:               `${REVIEW_URL}/ebook`,
    UPDATE_REVIEW_VOICE:               `${REVIEW_URL}/voice-model`,

    // GET
    LOAD_REVIEW_EBOOKS:              `${REVIEW_URL}/ebook?ebookId=&size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=`,
    LOAD_REVIEW_VOICES:              `${REVIEW_URL}/voice-model?voiceModelId=&size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,

    NUM_PAGES_REVIEW_EBOOKS:         `${REVIEW_URL}/ebook/total?ebookId=&size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}`,
    NUM_PAGES_REVIEW_VOICES:         `${REVIEW_URL}/voice-model/total?voiceModelId=&size=${PageConfig.VOICE_PRODUCT_PER_PAGE}`,

    // DELETE
    DELETE_REVIEW_EBOOK:              `${REVIEW_URL}/ebook`,
    DELETE_REVIEW_VOICE:              `${REVIEW_URL}/voice-model`,

    // Wish
    // POST
    WISH_EBOOK:                      `${WISH_URL}/ebook?ebookId=`,
    WISH_VOICE:                      `${WISH_URL}/voice-model?voiceModelId=`,
    WISH_VERIFY_EBOOK:              `${WISH_URL}/ebook/{ebookId}/verify`,
    WISH_VERIFY_VOICE:              `${WISH_URL}/voice-model/{voiceModelId}/verify`,

    // GET
    LOAD_WISH_EBOOKS:              `${WISH_URL}/ebook?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&page=`,
    LOAD_WISH_VOICES:              `${WISH_URL}/voice-model?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&page=`,


}