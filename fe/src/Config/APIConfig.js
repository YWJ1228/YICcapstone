import { PageConfig } from "./Config";

const BASE_URL = "http://localhost:8080";
const EBOOK_URL = `${BASE_URL}/ebook`;
const VOICE_URL = `${BASE_URL}/voice-model`;

export const API = {
    // #########  우준 API  ########
    // GET
    VERIFY_EMAIL:       `${BASE_URL}/api/sign-up/username`,
    CHECK_NICKNAME:     `${BASE_URL}/api/sign-up/nickname`,

    // POST
    REGISTER_USER:      `${BASE_URL}/api/sign-up`,
    LOGIN_USER:         `${BASE_URL}/api/log-in`,
    FIND_ID:            `${BASE_URL}/api/find/id`,
    FIND_PWD:           `${BASE_URL}/api/find/password`,

    // DELETE
    DELETE_USER:        `${BASE_URL}/api/user`,

    // PATCH
    CHANGE_PWD:         `${BASE_URL}/api/user/password`,
    CHANGE_NICKNAME:    `${BASE_URL}/api/user/nickname`,


    // #########  태훈 API  #############
    // GET
    // EBOOK
    LOAD_EBOOK :                    `${EBOOK_URL}`,
    LOAD_ALL_EBOOKS :               `${EBOOK_URL}/list?page=`,
    LOAD_CATEGORY_EBOOKS :          `${EBOOK_URL}/list/category?classification=`,
    NUM_PAGES_EBOOKLIST :           `${EBOOK_URL}/list/total?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}`,
    NUM_PAGES_CATEGORY_EBOOKLIST :  `${EBOOK_URL}/list/total/category?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&classification=`,
    // VOICE
    LOAD_VOICE :                    `${VOICE_URL}`,
    LOAD_ALL_VOICES :               `${VOICE_URL}/list?page=`,
    LOAD_CATEGORY_VOICES :          `${VOICE_URL}/list/category?job=`,
    NUM_PAGES_VOICELIST :           `${VOICE_URL}/list/total?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}`,
    NUM_PAGES_CATEGORY_VOICELIST :  `${VOICE_URL}/list/total/category?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&job=`,

}