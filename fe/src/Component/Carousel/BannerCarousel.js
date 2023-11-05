import Paper from "@mui/material/Paper";
import Carousel from "react-material-ui-carousel";
import classes from "./BannerCarousel.module.css";
export default function BannerCarousel() {
  const bannerAPI = ["/Image/Banner_Image/Banner001_img.png", "/Image/Banner_Image/Banner002_img.png", "/Image/Banner_Image/Banner003_img.png"];
  const bannerCarouselItem = bannerAPI.map((banner) => {
    return (
      <Paper className={classes["img-wrapper"]}>
        <img src={banner} className={classes.img}></img>
      </Paper>
    );
  });
  return (
    <Carousel className={classes.carousel} animation="slide" duration = {600} navButtonsAlwaysInvisible={true} indicators={false}>
      {bannerCarouselItem}
    </Carousel>
  );
}
