
import MyPageBanner from './MyPageBanner';
import MyPageMenu from './MyPageMenu';

import classes from './MyPage.module.css';

export default function MyPage(){
    return(
        <>
            <MyPageBanner/>
            <div className = {classes.menu}>
            <MyPageMenu />
            </div>
        </>
    )
}