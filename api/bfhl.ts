import type { VercelRequest, VercelResponse } from "@vercel/node";
import {
  errorResponse,
  processBfhl,
  type BfhlRequestBody,
} from "../lib/bfhlProcessor";

export default function handler(req: VercelRequest, res: VercelResponse) {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
  res.setHeader("Access-Control-Allow-Headers", "Content-Type");

  if (req.method === "OPTIONS") {
    return res.status(200).end();
  }

  if (req.method !== "POST") {
    return res.status(405).json(errorResponse());
  }

  try {
    const body = (req.body ?? {}) as BfhlRequestBody;
    if (!body.data || !Array.isArray(body.data)) {
      return res.status(400).json(errorResponse());
    }
    return res.status(200).json(processBfhl(body));
  } catch {
    return res.status(500).json(errorResponse());
  }
}
