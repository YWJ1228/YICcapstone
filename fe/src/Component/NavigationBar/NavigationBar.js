import { useNavigate } from "react-router-dom";
import { Form } from "react-bootstrap";
import { useState } from "react";
import { getCookies, removeCookies } from "../Cookies/LoginCookie";

import * as React from "react";
import { styled, alpha } from "@mui/material/styles";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import InputBase from "@mui/material/InputBase";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import SearchIcon from "@mui/icons-material/Search";
import AccountCircle from "@mui/icons-material/AccountCircle";
import LogoutIcon from "@mui/icons-material/Logout";
import LoginIcon from "@mui/icons-material/Login";
import PersonAddAlt1Icon from "@mui/icons-material/PersonAddAlt1";
import Stack from "@mui/material/Stack";
import Collapse from '@mui/material/Collapse';

import classes from "./NavigationBar.module.css";
const Search = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  "&:hover": {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginRight: theme.spacing(2),
  marginLeft: 0,
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(3),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  "& .MuiInputBase-input": {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("md")]: {
      width: "20ch",
    },
  },
}));

function NavigationBar(props) {
  const showMenuBarState = props.showMenuBar;
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = React.useState(null);

  const isMenuOpen = Boolean(anchorEl);

  const navigateLogin = useNavigate();

  const handleProfileMenuOpen = (event) => {
    // setAnchorEl(event.currentTarget);
    if (getCookies("accessToken") !== undefined) {
      navigateLogin("/mypage");
    } else {
      navigateLogin("/login");
    }
  };

  const handleMobileMenuClose = () => {
    setMobileMoreAnchorEl(null);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
    handleMobileMenuClose();
  };

  const menuId = "primary-search-account-menu";
  const renderMenu = (
    <Menu
      anchorEl={anchorEl}
      anchorOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      id={menuId}
      keepMounted
      transformOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      open={isMenuOpen}
      onClose={handleMenuClose}
    >
      <MenuItem onClick={handleMenuClose}>Profile</MenuItem>
      <MenuItem onClick={handleMenuClose}>My account</MenuItem>
    </Menu>
  );

  const navigateHome = useNavigate();
  const [searchText, setSearchText] = useState();
  const [showMenu, setShowMenu] = useState(showMenuBarState);
  const menuHandler = () => {
    setShowMenu(!showMenu);
  };
  function searchChangeHandler(event) {
    setSearchText(event.target.value);
  }
  function submitHandler(event) {
    event.preventDefault();
    navigateHome(`/search-result?keyword=${searchText}`);
  }
  function logoutHandler() {
    removeCookies("accessToken");
    alert("로그아웃 되었습니다!");
    navigateHome("/");
  }
  function loginHandler() {
    navigateHome("/login");
  }
  function registerHandler() {
    navigateHome("/register");
  }

  return (
    <>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar component="nav" className={classes["menu-nav"]}>
          <Toolbar>
            {/* 메뉴 아이콘 */}
            <IconButton size="large" edge="start" color="inherit" aria-label="open drawer" sx={{ mr: 2 }} onClick={menuHandler}>
              <MenuIcon />
            </IconButton>

            <Typography
              variant="h6"
              noWrap
              component="div"
              sx={{ display: { xs: "none", sm: "block" } }}
              onClick={() => {
                navigateHome("/");
              }}
              className={classes.banner}
            >
              최애의 보이스
            </Typography>
            {/* 검색 아이콘 */}
            <Form onSubmit={submitHandler}>
              <Search>
                <SearchIconWrapper>
                  <SearchIcon />
                </SearchIconWrapper>
                <StyledInputBase placeholder="Search…" name="searchForm" inputProps={{ "aria-label": "search" }} onChange={searchChangeHandler} />
              </Search>
            </Form>
            {/* 내 정보 버튼 */}
            <Box sx={{ flexGrow: 1 }} />
            <Box sx={{ display: { xs: "none", md: "flex" } }}>
              {getCookies("accessToken") === undefined && (
                <IconButton size="large" edge="end" aria-label="account of current user" aria-controls={menuId} aria-haspopup="true" onClick={registerHandler} color="inherit">
                  <PersonAddAlt1Icon />
                </IconButton>
              )}
              {getCookies("accessToken") === undefined && (
                <IconButton size="large" edge="end" aria-label="account of current user" aria-controls={menuId} aria-haspopup="true" onClick={loginHandler} color="inherit">
                  <LoginIcon />
                </IconButton>
              )}
              {getCookies("accessToken") !== undefined && (
                <IconButton size="large" edge="end" aria-label="account of current user" aria-controls={menuId} aria-haspopup="true" onClick={handleProfileMenuOpen} color="inherit">
                  <AccountCircle />
                </IconButton>
              )}
              {getCookies("accessToken") !== undefined && (
                <IconButton size="large" edge="end" aria-label="account of current user" aria-controls={menuId} aria-haspopup="true" onClick={logoutHandler} color="inherit">
                  <LogoutIcon />
                </IconButton>
              )}
            </Box>
          </Toolbar>
          <Collapse in = {showMenu}>
            <Box className={classes["menu-accordian"]}>
              <Stack direction="row" spacing={15}>
                <Typography className={classes.menu}> </Typography>
                <Typography
                  className={classes.menu}
                  onClick={() => {
                    navigateHome("/bookShop");
                  }}
                >
                  책
                </Typography>
                <Typography
                  className={classes.menu}
                  onClick={() => {
                    navigateHome("/voiceShop");
                  }}
                >
                  음성모델
                </Typography>
              </Stack>
            </Box>
            </Collapse>
        </AppBar>
        {renderMenu}
      </Box>
    </>
  );
}
export default NavigationBar;
