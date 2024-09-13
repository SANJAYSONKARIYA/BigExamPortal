(function () {
  "use strict";

  function toggleScrolled() {
    const selectBody = document.querySelector("body");
    const selectHeader = document.querySelector("#header");
    if (
      !selectHeader.classList.contains("scroll-up-sticky") &&
      !selectHeader.classList.contains("sticky-top") &&
      !selectHeader.classList.contains("fixed-top")
    )
      return;
    window.scrollY > 100
      ? selectBody.classList.add("scrolled")
      : selectBody.classList.remove("scrolled");
  }

  document.addEventListener("scroll", toggleScrolled);
  window.addEventListener("load", toggleScrolled);

  const mobileNavToggleBtn = document.querySelector(".mobile-nav-toggle");

  function mobileNavToogle() {
    document.querySelector("body").classList.toggle("mobile-nav-active");
    mobileNavToggleBtn.classList.toggle("bi-list");
    mobileNavToggleBtn.classList.toggle("bi-x");
  }
  mobileNavToggleBtn.addEventListener("click", mobileNavToogle);

  document.querySelectorAll("#navmenu a").forEach((navmenu) => {
    navmenu.addEventListener("click", () => {
      if (document.querySelector(".mobile-nav-active")) {
        mobileNavToogle();
      }
    });
  });

  document.querySelectorAll(".navmenu .toggle-dropdown").forEach((navmenu) => {
    navmenu.addEventListener("click", function (e) {
      if (document.querySelector(".mobile-nav-active")) {
        e.preventDefault();
        this.parentNode.classList.toggle("active");
        this.parentNode.nextElementSibling.classList.toggle("dropdown-active");
        e.stopImmediatePropagation();
      }
    });
  });

  const preloader = document.querySelector("#preloader");
  if (preloader) {
    window.addEventListener("load", () => {
      preloader.remove();
    });
  }

  let scrollTop = document.querySelector(".scroll-top");

  function toggleScrollTop() {
    if (scrollTop) {
      window.scrollY > 100
        ? scrollTop.classList.add("active")
        : scrollTop.classList.remove("active");
    }
  }
  scrollTop.addEventListener("click", (e) => {
    e.preventDefault();
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  });

  window.addEventListener("load", toggleScrollTop);
  document.addEventListener("scroll", toggleScrollTop);

  function aosInit() {
    AOS.init({
      duration: 600,
      easing: "ease-in-out",
      once: true,
      mirror: false,
    });
  }
  window.addEventListener("load", aosInit);

  const glightbox = GLightbox({
    selector: ".glightbox",
  });

  // new PureCounter();

  function initSwiper() {
    document.querySelectorAll(".swiper").forEach(function (swiper) {
      let config = JSON.parse(
        swiper.querySelector(".swiper-config").innerHTML.trim()
      );
      new Swiper(swiper, config);
    });
  }
  window.addEventListener("load", initSwiper);

  // Counts Section - index & about page
  // Function to generate a random number between min and max
  function getRandomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  // Function to update counter values
  function updateCounters() {
    const counters = {
      students: { min: 400, max: 500 },
      institutes: { min: 18, max: 20 },
      events: { min: 12, max: 15 },
      trainers: { min: 18, max: 20 }
    };

    Object.keys(counters).forEach(key => {
      const element = document.getElementById(`${key}-counter`);
      const currentValue = parseInt(element.textContent) || 0;
      const { min, max } = counters[key];
      const newValue = getRandomNumber(min, max);

      // Ensure the new value is within the min and max range
      const incrementValue = Math.min(Math.max(newValue, currentValue + 1), max);
      element.textContent = incrementValue;
    });
  }

  // Call the updateCounters function when the page loads
  window.onload = updateCounters;
  
  // Google Maps - Contact Page
  document.addEventListener('DOMContentLoaded', function() {
      const form = document.getElementById('directionsForm');
      const iframe = document.getElementById('mapsIframe');
      
      form.addEventListener('submit', function(event) {
          event.preventDefault();
          
          const origin = encodeURIComponent(document.getElementById('origin').value);
          const destination = 'NSTI+Dehradun';  // Static destination for this example
          const apiKey = 'AIzaSyAVPMw4fcUCPq5IopMBFiiSJXfrC3EuKJo';  // Replace with your actual API key
          
          const url = `https://www.google.com/maps/embed/v1/directions?key=${apiKey}&origin=${origin}&destination=${destination}&mode=driving`;
          
          iframe.src = url;
      });
  });
  
})();
