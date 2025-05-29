// components/AnimatedNumberTypewriter.tsx
import { useMotionValue, animate } from "framer-motion";
import { useEffect, useState } from "react";
import type { FC } from "react";

interface AnimatedNumberTypewriterProps {
  value: number;
  locale?: string;
  duration?: number;
}

const AnimatedNumberTypewriter: FC<AnimatedNumberTypewriterProps> = ({
  value,
  locale = "ru-RU",
  duration = 1.2,
}) => {
  const [displayValue, setDisplayValue] = useState(value);
  const motionValue = useMotionValue(value);

  useEffect(() => {
    const controls = animate(motionValue, value, {
      duration,
      ease: "easeOut",
      onUpdate: (latest) => setDisplayValue(latest),
    });

    return () => controls.stop();
  }, [value, duration, motionValue]);

  return <span>{Math.round(displayValue).toLocaleString(locale)}</span>;
};

export default AnimatedNumberTypewriter;
