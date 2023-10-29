import classes from './DetailStyle1.module.css';
import { Paper } from '@mui/material'
import Carousel from 'react-material-ui-carousel'
import DetailStyle1Component from '../../Component/Card/CarouselComponent1';

export default function DetailStyle1(props) {
    const bookArr = [];
    for (let i = 0; i < props.books.length; i++) {
        bookArr.push(
            [
                props.books[i == 0 ? props.books.length - 1 : i - 1],
                props.books[i],
                props.books[(i + 1) % props.books.length]
            ]
        )
    }
    const carouselItems = bookArr.map((bookTriple) => {
        return (
            <Paper className={classes['wrapper-paper']}>
                <div className={classes.wrapper}>
                    <DetailStyle1Component items={bookTriple} />
                </div>
            </Paper>
        );
    })

    return (
        <Carousel indicators={false}>

            {carouselItems}

        </Carousel>

    )
}