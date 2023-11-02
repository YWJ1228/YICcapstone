import { Cookies } from "react-cookie";

const cookies = new Cookies();

export const setCookies = (name, token, options) => {
  return cookies.set(name, token, { ...options });
};
export const getCookies = (name) => {
  // refresh token을 내보냄
  return cookies.get(name);
};
export const removeCookies = (name) => {
  return cookies.remove(name);
};
