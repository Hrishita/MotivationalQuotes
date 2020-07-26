class cached_text {
  constructor() {
    this.texts = {};
  }
  getLHS = (position, category) => {
    return `${position}_${category}`;
  };

  getItemAtPositionAndCategory = (position, category) => {
    const lhs = this.getLHS(position, category);
    return this.texts[lhs];
  };
  removeItemAtPosition = (position, category) => {
    const lhs = this.getLHS(position, category);
    delete this.texts[lhs];
  };
  addItemAtPosition = (position, category, text) => {
    if (this.texts.length >= 1000) {
      const firstObject = Object.keys(this.texts)[0];
      delete this.texts[firstObject];
    }
    const lhs = this.getLHS(position, category);
    this.texts[lhs] = text;
  };
}

c = new cached_text();
module.exports = {
  quick: c,
};
