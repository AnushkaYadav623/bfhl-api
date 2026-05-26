export const USER = {
  userId: "anushka_yadav_18022006",
  email: "anushkayadav230422@acropolis.in",
  rollNumber: "0827IT231027",
} as const;

export interface BfhlRequestBody {
  data?: string[] | null;
}

export interface BfhlResponseBody {
  is_success: boolean;
  user_id: string;
  email: string;
  roll_number: string;
  odd_numbers: string[];
  even_numbers: string[];
  alphabets: string[];
  special_characters: string[];
  sum: string;
  concat_string: string;
}

function isNumeric(value: string): boolean {
  if (value.startsWith("-")) {
    return value.length > 1 && value.slice(1).split("").every((c) => /\d/.test(c));
  }
  return /^\d+$/.test(value);
}

function isAlphabetic(value: string): boolean {
  return /^[a-zA-Z]+$/.test(value);
}

function buildConcatString(chars: string[]): string {
  if (chars.length === 0) return "";
  const reversed = [...chars].reverse();
  return reversed
    .map((c, i) => (i % 2 === 0 ? c.toUpperCase() : c.toLowerCase()))
    .join("");
}

export function processBfhl(body: BfhlRequestBody): BfhlResponseBody {
  const data = body.data ?? [];

  const oddNumbers: string[] = [];
  const evenNumbers: string[] = [];
  const alphabets: string[] = [];
  const specialCharacters: string[] = [];
  const alphaCharsForConcat: string[] = [];
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

export function errorResponse(): BfhlResponseBody {
  return {
    is_success: false,
    user_id: USER.userId,
    email: USER.email,
    roll_number: USER.rollNumber,
    odd_numbers: [],
    even_numbers: [],
    alphabets: [],
    special_characters: [],
    sum: "0",
    concat_string: "",
  };
}
