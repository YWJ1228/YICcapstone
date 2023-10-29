
import classes from './CarouselComponent1.module.css';
export default function DetailStyle1Component(props) {
    return (
        (props.items).map((item) => {
            return (
                <div>
                    <img src={item.image} className={classes.image} />
                    <h2>{item.name}</h2>
                </div>
            );

        })
    )
}