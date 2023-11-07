import { getCookies, removeCookies } from "../Cookies/LoginCookie";
import { Container, Navbar, Nav, Button } from "react-bootstrap";

import classes from "./NavigationBar.module.css";

function NavigationBar(props) {
  return (
    <div className={classes.wrapper}>
      <Navbar className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="/" className={classes.brand}>
            <img alt="" src="/logo.png" width="30" height="30" className="d-inline-block align-top" /> 최애의 보이스
          </Navbar.Brand>
        </Container>
        <Nav>
          <Nav.Item>
            <Nav.Link href="/bookShop" className={classes.menu}>
              책
            </Nav.Link>
          </Nav.Item>
          <Nav.Item>
            <Nav.Link href="/voiceShop" className={classes.menu}>
              음성모델
            </Nav.Link>
          </Nav.Item>
          <Nav.Item>
            <Nav.Link href={getCookies("accessToken") === undefined ? "/login" : "/mypage"} className={classes.menu}>
              내 서재
            </Nav.Link>
          </Nav.Item>
        </Nav>
        {getCookies("accessToken") === undefined && (
          <Button href="/login" className={classes["login-button"]}>
            <div className={classes.menu}>로그인</div>
          </Button>
        )}
        {getCookies("accessToken") !== undefined && (
          <Button href="/" className={classes["login-button"]} onClick={() => removeCookies("accessToken")}>
            <div className={classes.menu}>로그아웃</div>
          </Button>
        )}
      </Navbar>
      <div className={classes.divider} />
    </div>
  );
}
export default NavigationBar;
