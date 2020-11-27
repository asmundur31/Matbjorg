var allInputs = document.querySelectorAll('input[type="checkbox"]');
var allAds = document.querySelectorAll('.advertisement');
var selected = new Set();

allInputs.forEach((input) => {
    input.addEventListener('change', (event) => {
      var id = event.target.id;
      if(event.target.checked) {
        selected.add(id);
      } else {
        selected.delete(id);
      }
      updateFilter();
  })
})

function updateFilter() {
    if(selected.size == 0) {
        allAds.forEach((ad) => {
            show(ad);
        })
    } else {
        allAds.forEach((ad) => {
            hide(ad);
            if(ad.hasAttribute('data-tags')) {
                var classList = ad.dataset.tags.split(' ');
                classList.push(ad.dataset.owner);
                classList.forEach((tag) => {
                    if(selected.has(tag)) {
                        show(ad);
                    }
                })
            }
        })
    }
}

function show(ad) {
    ad.parentElement.setAttribute('style', 'display: block');
}

function hide(ad) {
    ad.parentElement.setAttribute('style', 'display: none');
}