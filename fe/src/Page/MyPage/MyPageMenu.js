import Tab from 'react-bootstrap/Tab';

import Nav from 'react-bootstrap/Nav';

import PurchaseBook from './PurchaseBook';
import LikeList from './LikeList';
import MyPageReview from './MyPageReview';

import classes from './MyPageMenu.module.css';

export default function MyPageMenu() {
    return (
        <Tab.Container className={classes.tab} defaultActiveKey="first">
            <Nav className = {classes['nav-wrapper']}>
                <Nav.Item><Nav.Link eventKey = "first" className = {classes['nav-link']}>책 목록</Nav.Link></Nav.Item>
                <Nav.Item><Nav.Link eventKey = "second" className = {classes['nav-link']}>리뷰 작성</Nav.Link></Nav.Item>
                <Nav.Item><Nav.Link eventKey = "third" className = {classes['nav-link']}>찜 목록</Nav.Link></Nav.Item>
            </Nav>
            <Tab.Content >
                <Tab.Pane eventKey="first"><PurchaseBook /></Tab.Pane>
                <Tab.Pane eventKey="second"><MyPageReview /></Tab.Pane>
                <Tab.Pane eventKey="third"> <LikeList /></Tab.Pane>
            </Tab.Content >
        </Tab.Container >
    )
}