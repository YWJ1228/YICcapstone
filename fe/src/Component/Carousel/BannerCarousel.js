import Paper from "@mui/material/Paper";
import Carousel from "react-material-ui-carousel";
import classes from "./BannerCarousel.module.css";
export default function BannerCarousel(props) {
  const pageType = props.type
  const bannerAPI = ["/Image/Banner_Image/Banner001_img.png", "/Image/Banner_Image/Banner002_img.png", "/Image/Banner_Image/Banner003_img.png"];
  const bookBannerAPI = ["/Image/Banner_Image/BookBanner001_img.png","/Image/Banner_Image/BookBanner002_img.png"]
  var banner = bannerAPI;
  switch(props.type){
    case 'home' : banner = bannerAPI; break;
    case 'book' : banner = bookBannerAPI; break;
  }
  const bannerCarouselItem = (banner).map((banner, idx) => {
    return (
      <Paper className={classes["img-wrapper"]} key = {idx}>
        <img src={banner} className={classes.img}></img>
      </Paper>
    );
  });

  return (
    <Carousel className={classes.carousel} animation="slide" duration = {600} navButtonsAlwaysvisible={false} indicators={false}>
      {bannerCarouselItem}
    </Carousel>
  );
}
