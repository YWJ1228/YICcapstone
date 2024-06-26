export const IS_DEBUGGING = false; // 디버깅 모드 선택 가능
export const DebuggingMode = (description, data) => {
  if (IS_DEBUGGING) {
    description.map((text, idx) => {
      // console.log(text, data[idx]);
    });
  }
};
export const creditConfig = {
  imp: "imp40848253",
  pg: "KCP.A52CY",
};

export const categoryDict = {
  '시': "시",
  '소설': "소설",
  '가수' : '가수',
  '배우' : '배우'
};

export const PageConfig = {
  // Home Page Config
  HOMEPAGE_TITLES: [
    { title: "이 달의 책", subtitle: "이 달의 가장 인기 있는 책을 만나보세요" },
    { title: "이 달의 TTS", subtitle: "이 달의 가장 인기 있는 TTS를 만나보세요" },
  ],
  SORT_TYPE: ["업로드순", "인기순", "낮은 가격 순", "높은 가격순"],
  DEFAULT_SORT_TYPE: 0,

  // Ebook Shop Config
  EBOOK_SHOP_TITLES: [
    { title: "이 달의 책", subtitle: "이달의 가장 인기있는 책을 만나보세요" },
    { title: "업데이트 된 책", subtitle: "새로 업데이트 된 책들의 오디오북을 들어보세요" },
  ],
  EBOOK_PAGE_DEFAULT_STATE: {
    id: "default",
    image: "default",
    name: "default",
    description: "default",
    price: "default,",
  },
  EBOOK_CATEGORY_LIST: ["전체", "소설", "시"],
  EBOOK_PRODUCT_PER_PAGE: 3,

  NUM_BANNER_EBOOKS: 4,
  NUM_POPULAR_EBOOKS: 5,
  NUM_RECENT_EBOOKS: 5,
  NUM_EBOOK_REVIEWS: 5,
  NUM_EBOOK_PURCHASED: 5,
  NUM_REVIEWS_FOR_EBOOK : 5,
  NUM_REVIEWS_FOR_VOICE : 5,

  // Voice Shop Config
  VOICE_SHOP_TITLES: [
    { title: "이 달의 TTS", subtitle: "이달의 가장 인기있는 목소리를 만나보세요" },
    { title: "업데이트 된 TTS", subtitle: "새로 업데이트 된 TTS를 들어보세요" },
  ],
  VOICE_PAGE_DEFAULT_STATE: {
    id: "default",
    image: "default",
    name: "default",
    job: "default",
    price: 0,
    description: "default",
    demoUrl: "default",
    voiceUrl: "default",
  },
  VOICE_CATERGORY_LIST: ["전체", "가수", "배우"],
  VOICE_PRODUCT_PER_PAGE: 2,

  NUM_BANNER_VOICES: 4,
  NUM_POPULAR_VOICES: 3,
  NUM_RECENT_VOICES: 5,
};
