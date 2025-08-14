#!/usr/bin/env python3
"""
Simple Icedrive Backup Verification Script
Checks if files from source folders exist in the mounted I drive
"""

import os
from pathlib import Path
from datetime import datetime
import argparse

def scan_directory(directory):
    """Recursively scan directory and return list of relative file paths"""
    files = []
    base_path = Path(directory)
    
    if not base_path.exists():
        print(f"Error: Directory {directory} does not exist")
        return files
    
    for root, dirs, filenames in os.walk(directory):
        for filename in filenames:
            full_path = Path(root) / filename
            relative_path = full_path.relative_to(base_path)
            files.append({
                'relative_path': str(relative_path),
                'full_path': str(full_path)
            })
    
    return files

def verify_backup(source_dirs, backup_root, verbose=False):
    """Check if source files exist in backup location"""
    missing_files = []
    
    backup_path = Path(backup_root)
    if not backup_path.exists():
        print(f"Error: Backup directory {backup_root} does not exist or is not mounted")
        return None
    
    print(f"Backup verification started at {datetime.now()}")
    print(f"Backup location: {backup_root}")
    print("-" * 50)
    
    for source_dir in source_dirs:
        print(f"Checking source: {source_dir}")
        source_files = scan_directory(source_dir)
        
        if not source_files:
            print(f"  No files found in {source_dir}")
            continue
            
        print(f"  Found {len(source_files)} files to verify")
        
        # Determine backup subdirectory
        source_name = Path(source_dir).name
        backup_subdir = backup_path / source_name
        
        for file_info in source_files:
            rel_path = file_info['relative_path']
            backup_file_path = backup_subdir / rel_path
            
            if verbose:
                print(f"    Checking: {rel_path}")
            
            # Check if file exists in backup
            if not backup_file_path.exists():
                missing_files.append({
                    'source': file_info['full_path'],
                    'expected_backup': str(backup_file_path),
                    'relative_path': rel_path,
                    'source_dir': source_dir
                })
                if verbose:
                    print(f"      MISSING: {rel_path}")
    
    # Print summary
    print("\n" + "=" * 50)
    print("BACKUP VERIFICATION SUMMARY")
    print("=" * 50)
    
    print(f"Total missing files: {len(missing_files)}")
    
    if missing_files:
        print(f"\nMISSING FILES:")
        
        # Group by source directory for cleaner output
        by_source = {}
        for item in missing_files:
            source = item['source_dir']
            if source not in by_source:
                by_source[source] = []
            by_source[source].append(item)
        
        for source_dir, files in by_source.items():
            print(f"\n  From {source_dir}: ({len(files)} missing)")
            for item in files[:20]:  # Show first 20 per directory
                print(f"    {item['relative_path']}")
            if len(files) > 20:
                print(f"    ... and {len(files) - 20} more")
    else:
        print("\n✅ All files found in backup!")
    
    return missing_files

def save_missing_list(missing_files, output_file):
    """Save list of missing files to text file"""
    with open(output_file, 'w') as f:
        f.write(f"Missing Files Report - {datetime.now()}\n")
        f.write("=" * 50 + "\n\n")
        
        by_source = {}
        for item in missing_files:
            source = item['source_dir']
            if source not in by_source:
                by_source[source] = []
            by_source[source].append(item)
        
        for source_dir, files in by_source.items():
            f.write(f"Source: {source_dir}\n")
            f.write(f"Missing: {len(files)} files\n")
            f.write("-" * 30 + "\n")
            for item in files:
                f.write(f"{item['relative_path']}\n")
            f.write("\n")

def main():
    parser = argparse.ArgumentParser(description='Simple Icedrive backup verification')
    parser.add_argument('source_dirs', nargs='+', help='Source directories to check')
    parser.add_argument('--backup-root', '-b', required=True, 
                       help='Root of mounted Icedrive (e.g., I:\\ or /mnt/icedrive)')
    parser.add_argument('--verbose', '-v', action='store_true',
                       help='Show each file being checked')
    parser.add_argument('--output', '-o', help='Save missing files list to text file')
    
    args = parser.parse_args()
    
    # Verify all source directories exist
    for source_dir in args.source_dirs:
        if not Path(source_dir).exists():
            print(f"Error: Source directory {source_dir} does not exist")
            return 1
    
    missing_files = verify_backup(args.source_dirs, args.backup_root, args.verbose)
    
    if missing_files is None:
        return 1
    
    # Save missing files list if requested
    if args.output and missing_files:
        save_missing_list(missing_files, args.output)
        print(f"\nMissing files list saved to: {args.output}")
    
    # Return appropriate exit code
    if missing_files:
        print(f"\n⚠️  {len(missing_files)} files are missing from backup!")
        return 1
    else:
        print(f"\n✅ Backup verification completed - all files present!")
        return 0

if __name__ == "__main__":
    exit(main())