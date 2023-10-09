
import MyPageBanner from './MyPageBanner';
import MyPageMenu from './MyPageMenu';

import classes from './MyPage.module.css';

export default function MyPage(){
    return(
        <>
            <MyPageBanner imagePath = "./logo192.png" username = "OO"/>
            <div className = {classes.menu}>
            <MyPageMenu />
            </div>
        </>
    )
}