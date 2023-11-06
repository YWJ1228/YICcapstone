import MyPageBanner from "./MyPageBanner";
import MyPageMenu from "./MyPageMenu";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import classes from "./MyPage.module.css";

export default function MyPage() {
  return (
    <>
      <NavigationBar img_src="logo.png" />
      <div style={{ width: "100%", height: "6rem" }} />
      <MyPageBanner />
      <div className={classes.menu}>
        <MyPageMenu />
      </div>
    </>
  );
}
