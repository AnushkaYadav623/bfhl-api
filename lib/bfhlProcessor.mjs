export const USER = {
  userId: "anushka_yadav_18022006",
  email: "anushkayadav230422@acropolis.in",
  rollNumber: "0827IT231027",
};

function isNumeric(value) {
  if (value.startsWith("-")) {
    return value.length > 1 && value.slice(1).split("").every((c) => /\d/.test(c));
  }
  return /^\d+$/.test(value);
}

function isAlphabetic(value) {
  return /^[a-zA-Z]+$/.test(value);
}

function buildConcatString(chars) {
  if (chars.length === 0) return "";
  return [...chars]
    .reverse()
    .map((c, i) => (i % 2 === 0 ? c.toUpperCase() : c.toLowerCase()))
    .join("");
}

export function processBfhl(body) {
  const data = body.data ?? [];
  const oddNumbers = [];
  const evenNumbers = [];
  const alphabets = [];
  const specialCharacters = [];
  const alphaCharsForConcat = [];
  let sum = 0;

  for (const item of data) {
    if (item == null || item === "") continue;
    if (isNumeric(item)) {
      const value = Number(item);
      sum += value;
      if (value % 2 === 0) evenNumbers.push(item);
      else oddNumbers.push(item);
    } else if (isAlphabetic(item)) {
      alphabets.push(item.toUpperCase());
      for (const c of item) {
        if (/[a-zA-Z]/.test(c)) alphaCharsForConcat.push(c);
      }
    } else {
      specialCharacters.push(item);
    }
  }

  return {
    is_success: true,
    user_id: USER.userId,
    email: USER.email,
    roll_number: USER.rollNumber,
    odd_numbers: oddNumbers,
    even_numbers: evenNumbers,
    alphabets,
    special_characters: specialCharacters,
    sum: String(sum),
    concat_string: buildConcatString(alphaCharsForConcat),
  };
}
