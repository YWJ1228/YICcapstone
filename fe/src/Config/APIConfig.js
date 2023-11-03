import { PageConfig } from "./Config";

const BASE_URL = "http://localhost:8080";

const EBOOK_URL = `${BASE_URL}/ebook`;
const VOICE_URL = `${BASE_URL}/voice-model`;

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
    // GET
    // EBOOK
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
    ADMIN_LOAD_USERLIST :               ``


}