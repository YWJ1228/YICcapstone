import { PageConfig } from "./PageConfig";

const BASE_URL = "http://localhost:8080";

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
    LOAD_EBOOK :                    `${BASE_URL}/ebook`,
    LOAD_ALL_EBOOKS :               `${BASE_URL}/ebook/list?page=`,
    LOAD_CATEGORY_EBOOKS :          `${BASE_URL}/ebook/list/category?classification=`,
    NUM_PAGES_EBOOKLIST :           `${BASE_URL}/ebook/list/total?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}`,
    NUM_PAGES_CATEGORY_EBOOKLIST :  `${BASE_URL}/ebook/list/total/category?size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}&classification=`,
    // VOICE
    LOAD_VOICE :                    `${BASE_URL}/voice-model`,
    LOAD_ALL_VOICES :               `${BASE_URL}/voice-model/list?page=`,
    LOAD_CATEGORY_VOICES :          `${BASE_URL}/voice-model/list/category?job=`,
    NUM_PAGES_VOICELIST :           `${BASE_URL}/voice-model/list/total?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}`,
    NUM_PAGES_CATEGORY_VOICELIST :  `${BASE_URL}/voice-model/list/total/category?size=${PageConfig.VOICE_PRODUCT_PER_PAGE}&job=`,

}