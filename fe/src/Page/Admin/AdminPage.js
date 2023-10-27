import Tab from 'react-bootstrap/Tab';

import Nav from 'react-bootstrap/Nav';

import AdminPageFirst from './AdminPageFirst';

import classes from './AdminPage.module.css'

export default function AdminPage() {
    return (
        <div className = {classes['tab-wrapper']}>
            <Tab.Container defaultActiveKey="first">
                <Nav className={classes['nav-wrapper']}>
                    <Nav.Item><Nav.Link eventKey="first" className={classes['nav-link']}>음성 목록 관리</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link eventKey="second" className={classes['nav-link']}>음성 모델 등록 및 관리</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link eventKey="third" className={classes['nav-link']}>책 등록 및 관리</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link eventKey="third" className={classes['nav-link']}>오디오 북 등록 및 관리</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link eventKey="third" className={classes['nav-link']}>회원관리</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link eventKey="third" className={classes['nav-link']}>종료</Nav.Link></Nav.Item>
                </Nav>
                <Tab.Content >
                    <Tab.Pane eventKey="first"><AdminPageFirst/></Tab.Pane>
                    <Tab.Pane eventKey="second"></Tab.Pane>
                    <Tab.Pane eventKey="third"> </Tab.Pane>
                </Tab.Content >
            </Tab.Container >
        </div>
    );
}