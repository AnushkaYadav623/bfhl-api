import { describe, it } from "node:test";
import assert from "node:assert/strict";
import { processBfhl } from "../lib/bfhlProcessor.mjs";

describe("processBfhl", () => {
  it("example A", () => {
    const r = processBfhl({ data: ["a", "1", "334", "4", "R", "$"] });
    assert.equal(r.is_success, true);
    assert.equal(r.user_id, "anushka_yadav_18022006");
    assert.equal(r.email, "anushkayadav230422@acropolis.in");
    assert.equal(r.roll_number, "0827IT231027");
    assert.deepEqual(r.odd_numbers, ["1"]);
    assert.deepEqual(r.even_numbers, ["334", "4"]);
    assert.deepEqual(r.alphabets, ["A", "R"]);
    assert.deepEqual(r.special_characters, ["$"]);
    assert.equal(r.sum, "339");
    assert.equal(r.concat_string, "Ra");
  });

  it("example B", () => {
    const r = processBfhl({
      data: ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"],
    });
    assert.deepEqual(r.odd_numbers, ["5"]);
    assert.deepEqual(r.even_numbers, ["2", "4", "92"]);
    assert.deepEqual(r.alphabets, ["A", "Y", "B"]);
    assert.deepEqual(r.special_characters, ["&", "-", "*"]);
    assert.equal(r.sum, "103");
    assert.equal(r.concat_string, "ByA");
  });
});
